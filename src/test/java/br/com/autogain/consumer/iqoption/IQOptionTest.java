package br.com.autogain.consumer.iqoption;

import br.com.autogain.consumer.iqoption.enums.Actives;
import br.com.autogain.consumer.iqoption.enums.BinaryBuyDirection;
import br.com.autogain.consumer.iqoption.enums.BinaryOptionId;
import br.com.autogain.consumer.iqoption.utils.IQUtils;
import br.com.autogain.consumer.iqoption.ws.message.Message;
import br.com.autogain.consumer.iqoption.ws.request.BaseRequestMessage;
import br.com.autogain.consumer.iqoption.ws.request.BinaryBuyRequest;
import br.com.autogain.consumer.iqoption.ws.request.Msg;
import br.com.autogain.domain.EventMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
@AutoConfigureMockMvc
public class IQOptionTest {

    @Autowired
    private IQOption iqOption;

    @BeforeEach
    public void init() {
        iqOption.initUser("geekcife@gmail.com", "klb8293pa");
        iqOption.connect();
    }

    @SneakyThrows
    @Test
    public void buyBinary() {
        iqOption.initUser("geekcife@gmail.com", "klb8293pa");
        iqOption.connect();
        if(iqOption.isAuthenticated()) {
            Long expiration = IQUtils.getExpiration(1);
            BinaryBuyRequest body = new BinaryBuyRequest(BinaryOptionId.getOptionIdByExpiration(1).getId(), BinaryBuyDirection.CALL.getDirection(), 10.0, this.iqOption.getActiveBalanceId(), expiration, Actives.EURUSD.getId());
            EventMessage eventMessage = null;
//            while (true) {
//                iqOption.getWebSocket().sendMessage(new BaseRequestMessage<Msg<BinaryBuyRequest>>("sendMessage", "buy", new Msg<BinaryBuyRequest>("binary-options.open-option", "1.0", body)));
//                List<Message> operationClosed = iqOption.getWebSocket().getSyncMsgQueue().stream()
//                        .filter(message -> message.getName().equals("option-closed"))
//                        .collect(Collectors.toList());
//                if(!operationClosed.isEmpty()) {
//                    break;
//                }
//            }
        }

//            List<Message> messages =  iqOption.getWebSocket().getSyncMsgQueue().stream()
//                    .filter(message -> message.getName().equals("option-closed"))
//                    .collect(Collectors.toList());
//
//            Optional<Message> message = Optional.empty();
//            if(!messages.isEmpty()) {
//                message = Optional.ofNullable(messages.get(messages.size()-1));
//            }
//            if (message.isPresent()) {
//                JSONObject jsonObject = new JSONObject(message.get().getMsg());
//                eventMessage = new ObjectMapper().readValue(jsonObject.toString(), EventMessage.class);
//            }
//            if(!messages.isEmpty()) {
////                break;
//            }
//        }
    }

}
