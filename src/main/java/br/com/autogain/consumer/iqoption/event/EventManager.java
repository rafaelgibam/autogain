package br.com.autogain.consumer.iqoption.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.autogain.config.ApplicationContextHolder;
import br.com.autogain.model.EventMessage;
import br.com.autogain.model.Message;
import br.com.autogain.converter.MessageConverter;
import br.com.autogain.repository.EventMessageRepository;
import br.com.autogain.service.EventMessageService;
import br.com.autogain.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EventManager {
	
	private final Logger logger = LoggerFactory.getLogger(EventManager.class);
	
	private Map<Events, List<EventListener>> subscribers = new HashMap<>();

	MessageConverter messageConverter;

	public EventManager() {
		messageConverter = new MessageConverter();
		for(Events ev: Events.values()) {
			subscribers.put(ev, new ArrayList<EventListener>());
		}
	}
	
	public void subscribe(Events ev, EventListener listener) {
		subscribers.get(ev).add(listener);
	}
	
	public void unsubscribe(Events ev, EventListener listener) {
		subscribers.get(ev).remove(listener);
	}
	
	public void notif(String originalMessage) {
		Message message = messageConverter.convertJSONtoMessage(originalMessage);
		Events ev = Events.get(message.getName());

		if(ev == null) {
//			logger.info("[EVENT] ignored: " + messageName + " " + originalMessage);
			if(message.getName().equals("option-opened")) {
				logger.info("[EVENT] ignored: " + message.getName() + " Operação em andamento...");
			}
			if(message.getName().equals("option-closed")){
				logger.info("[EVENT] ignored: " + message.getName() + " " + messageConverter.messageReturnOperation(originalMessage));
				EventMessageRepository eventMessageRepository = ApplicationContextHolder.getContext().getBean(EventMessageRepository.class);
				MessageService messageService = ApplicationContextHolder.getContext().getBean(MessageService.class);
				EventMessage eventMessage = messageConverter.convertMessageToOperationResponse(message);
				if(!eventMessageRepository.existsByIndex(eventMessage.getIndex())
				   && eventMessage.getResult().equals("win")
				   || eventMessage.getResult().equals("equal")
				   || eventMessage.getResult().equals("loose")) {
					eventMessageRepository.save(messageConverter.convertMessageToOperationResponse(message));
					messageService.save(message);
				}
			}
			return;
		}
		logger.debug("[EVENT] not ignored: " + message.getName());
		for(EventListener listener: subscribers.get(ev)) {
			listener.update(ev, originalMessage);
		}
	}

}
