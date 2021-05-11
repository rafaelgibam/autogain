package br.com.autogain.consumer.iqoption;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

import br.com.autogain.consumer.iqoption.enums.Actives;
import br.com.autogain.consumer.iqoption.enums.BalanceType;
import br.com.autogain.consumer.iqoption.enums.BinaryBuyDirection;
import br.com.autogain.consumer.iqoption.enums.BinaryOptionId;
import br.com.autogain.consumer.iqoption.event.EventListener;
import br.com.autogain.consumer.iqoption.event.EventManager;
import br.com.autogain.consumer.iqoption.event.Events;
import br.com.autogain.consumer.iqoption.factory.ResponseFactory;
import br.com.autogain.consumer.iqoption.service.ChangeBalanceBaseService;
import br.com.autogain.consumer.iqoption.service.LoginBaseService;
import br.com.autogain.consumer.iqoption.utils.IQUtils;
import br.com.autogain.consumer.iqoption.ws.message.Message;
import br.com.autogain.consumer.iqoption.ws.request.BaseRequestMessage;
import br.com.autogain.consumer.iqoption.ws.request.BinaryBuyRequest;
import br.com.autogain.consumer.iqoption.ws.request.CandleBody;
import br.com.autogain.consumer.iqoption.ws.request.Msg;
import br.com.autogain.consumer.iqoption.ws.response.Candle;
import br.com.autogain.domain.Balance;
import br.com.autogain.consumer.iqoption.ws.response.ProfileRootMessage;
import br.com.autogain.domain.EventMessage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.SneakyThrows;
import org.joda.time.DateTime;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import springfox.documentation.spring.web.json.Json;

@Service
public class IQOption implements EventListener {

	private final Logger logger = LoggerFactory.getLogger(IQOption.class);

	/*
	 * Authentication
	 */
	private String email;
	private String password;

	private boolean isAuthenticated = false;

	private String ssid = "";
	private String cookies = "";

	@Autowired
	private HttpHeaders headers;
	
	/**
	 * Profile
	 */
	@Getter
	private List<Balance> balances;
	@Getter
	private Long activeBalanceId;

	/**
	 * Event Manager
	 */
	@Autowired
	private EventManager eventManager;
	
	/*
	 * Web Socket
	 */
	@Getter
	private IQOptionWS webSocket;

	/*
	 * Services
	 */
	@Autowired
	private LoginBaseService loginService;
	@Autowired
	private ChangeBalanceBaseService changeBalanceBaseService;
	
	private void initListeners() {
		this.eventManager.subscribe(Events.PROFILE, this);
	}

	public void initUser(String email, String password) {
		initListeners();
		this.email = email;
		this.password = password;
	}

	/**
	 * check if websocket and api are fine
	 * if it returns false, one of them may have failed
	 * @return
	 */
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
	
	/**
	 * Gets the EventManager for this IQOption instance.
	 * It should be used to subscribe to events.
	 * @return instance of the EventManager
	 */
	public EventManager getEventManager() {
		return this.eventManager;
	}

	/**
	 * Connect to both api and websocket
	 */
	public void connect() {
		ResponseEntity<String> res = null;
		try {
			res = loginService.login(email, password);
		} catch (RestClientException e) {
			logger.error(e.getMessage());
			logger.error("[API] Could not login into IQOption..");
			return;
		}
		
		if (res != null && HttpStatus.OK.equals(res.getStatusCode())) {
			this.cookies = res.getHeaders().get("Set-Cookie").get(0);
			this.isAuthenticated = true;
			this.ssid = this.cookies.substring(5, this.cookies.indexOf(';'));
			this.headers.set(HttpHeaders.COOKIE, "ssid=" + this.ssid);
			logger.info("[API] Logged into IQOption rest api");
		}

		try {
			webSocket = new IQOptionWS(new URI("wss://iqoption.com/echo/websocket"));
			webSocket.addMessageHandler(new IQOptionMessageHandler(this.eventManager));
			
			if(!webSocket.getUserSession().isOpen()) {
				this.isAuthenticated = false;
				return;
			}

			webSocket.sendMessage(new BaseRequestMessage<String>("ssid", "", ssid));
		} catch (URISyntaxException e) {
			logger.error("[WS]" + e.getMessage());
		}

	}
	
	/**
	 * Method used to get the current balance.
	 * REAL or PRACTICE account
	 * @param profile
	 */
	private void getActiveBalance(ProfileRootMessage profile) {
		Long id = profile.getMsg().getBalanceId();
		Balance balance = profile.getMsg().getBalances()
			.stream()
			.filter(b -> b.getId().equals(id))
			.findFirst().get();

		logger.info("[BALANCE] Current balance is: " + BalanceType.get(balance.getType()));
		balances = profile.getMsg().getBalances();

		if(activeBalanceId == null) {
			logger.info("[BALANCE] API just started up, making sure we are on PRACTICE ACCOUNT");
			changeBalance(BalanceType.PRACTICE);
		}
	}
	
	/*
	 * ------------------------------------------------ 
	 *           Web Socket methods
	 * ------------------------------------------------
	 */
	
	/**
	 * Method to get candles
	 * !!Subscribe to the Events.CANDLE to get the response!!
	 * 
	 * IQUtils.getCurrentTime() to get the current time in seconds
	 * 
	 * @param count -  number of candles
	 * @param to - current time in seconds
	 * @param size - size in seconds of each candle  
	 * @param active - id of the ticker
	 */
	@SneakyThrows
	public List<Candle> getCandles(int count, long to, int size, Actives active ) {
		CandleBody body = new CandleBody(count, to, size, active.getId());
		List<Candle> candles = new ArrayList<>();

		while (true) {
			webSocket.sendMessage(new BaseRequestMessage<Msg<CandleBody>>("sendMessage", "", new Msg<CandleBody>("get-candles", "2.0", body)));
			if(this.isAuthenticated() && candles.isEmpty()) {
				List<Message> messages = webSocket.getSyncMsgQueue().stream()
					.filter(message -> message.getName().equals("candles"))
					.collect(Collectors.toList());
				messages.sort(Comparator.comparing(m -> m.getTimeCreated().getTime()));
				Optional<Message> message = Optional.empty();

				if(!messages.isEmpty()) {
					message = Optional.ofNullable(messages.get(messages.size()-1));
				}
				if (message.isPresent()) {
					JSONObject jsonObject = new JSONObject(message.get().getMsg());
					candles = new ObjectMapper().readValue(jsonObject.get("candles").toString(), new TypeReference<List<Candle>>(){});
				}
			} if(!candles.isEmpty()) {
				break;
			}
		}

		return candles;
	}

	@SneakyThrows
	public EventMessage getResultOperation() {
		EventMessage eventMessage = null;
		while(true) {
			List<Message> messages = webSocket.getSyncMsgQueue().stream()
					.filter(message -> message.getName().equals("option-closed"))
					.collect(Collectors.toList());
			messages.sort(Comparator.comparing(m -> m.getTimeCreated().getTime()));
			Optional<Message> message = Optional.empty();
			if(!messages.isEmpty()) {
				message = Optional.ofNullable(messages.get(messages.size()-1));
			}
			if (message.isPresent()) {
				JSONObject jsonObject = new JSONObject(message.get().getMsg());
				eventMessage = new ObjectMapper().readValue(jsonObject.toString(), EventMessage.class);
			}
			if(eventMessage != null) {
				break;
			}
		}
		return eventMessage;
	}
	
	/**
	 * !!Subscribe to Events.INITIALIZATION_DATA to get the response!!
	 * 
	 * This method returns a huge amount of data with all binary active 
	 * and its info.
	 */
	public void getAllBinaryData() {
		webSocket.sendMessage(new BaseRequestMessage<Msg<Object>>("sendMessage", "", new Msg<Object>("get-initialization-data", "3.0", new Object())));
	}
	
	/**
	 * Method to buy an binary option
	 * 
	 * Duration must be in minutes: 1 or 5 
	 * 
	 * @param price
	 * @param direction
	 * @param active
	 * @param duration
	 */
	@SneakyThrows
	public EventMessage buyBinary(Double price, BinaryBuyDirection direction, Actives active, int duration) {
		Long expiration = IQUtils.getExpiration(duration);
		BinaryBuyRequest body = new BinaryBuyRequest(BinaryOptionId.getOptionIdByExpiration(duration).getId(), direction.getDirection(), price, this.activeBalanceId, expiration, active.getId());
		EventMessage eventMessage = null;
		webSocket.getSyncMsgQueue().clear();
		webSocket.sendMessage(new BaseRequestMessage<Msg<BinaryBuyRequest>>("sendMessage", "buy", new Msg<BinaryBuyRequest>("binary-options.open-option", "1.0", body)));
		while (true) {

			List<Message> messages = webSocket.getSyncMsgQueue().stream()
					.filter(message -> message.getName().equals("option-closed"))
					.filter(message -> {
						DateTime dateTime = new DateTime(message.getTimeCreated()).plusMinutes(1);
						if(dateTime.isAfterNow()) {
							return true;
						}
						return false;
					})
					.collect(Collectors.toList());

			Optional<Message> message = Optional.empty();
			if(!messages.isEmpty()) {
				message = Optional.ofNullable(messages.get(messages.size()-1));
			}
			if (message.isPresent()) {
				JSONObject jsonObject = new JSONObject(message.get().getMsg());
				eventMessage = new ObjectMapper().readValue(jsonObject.toString(), EventMessage.class);
			}
			if(!messages.isEmpty()) {
				break;
			}
		}
		return eventMessage;
	}


	public Boolean verifyResult(EventMessage eventMessage) {
		if(eventMessage.getResult().equals("win")) {
			return true;
		} else if(eventMessage.getResult().equals("loose")) {
			return false;
		} else {
			return true;
		}
	}
	
	/*
	 * ------------------------------------------------ 
	 *          END of Web Socket methods
	 * ------------------------------------------------
	 */
	
	/*
	 * ------------------------------------------------ 
	 *           REST API methods
	 * ------------------------------------------------
	 */
	
	
	/**
	 * Change balance type
	 * Check BalanceType for reference
	 * @param type
	 * @return Response Entity
	 */
	public void changeBalance(BalanceType type) {
		Balance balance = balances
				.stream()
				.filter(b -> b.getType() == type.getId())
				.findFirst().get();
		activeBalanceId = balance.getId();
		changeBalanceBaseService.changeBalance(activeBalanceId, this.headers);
		logger.info("[BALANCE] changed to " + type);
	}
	
	/*
	 * ------------------------------------------------ 
	 *           End of REST API methods
	 * ------------------------------------------------
	 */
	@Override
	public void update(Events ev, String message) {
		if(Events.PROFILE.equals(ev)) {
			ProfileRootMessage profile = (ProfileRootMessage) ResponseFactory.transform(Events.PROFILE, message);
			getActiveBalance(profile);
		}
	}

}
