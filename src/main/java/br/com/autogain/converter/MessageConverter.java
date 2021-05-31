package br.com.autogain.converter;

import br.com.autogain.domain.EventMessage;
import br.com.autogain.domain.Message;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MessageConverter {

    @SneakyThrows
    public Message convertJSONtoMessage(String message) {
        Message messageParser = new Message();
        JsonNode node = new ObjectMapper().readTree(message);
        if(node.get("msg") != null && node.get("msg").isObject()) {
            messageParser.setMsg(node.get("msg").toString());
            messageParser.setName(node.get("name").asText());
            return messageParser;
        }
        return new ObjectMapper().readValue(message, Message.class);
    }

    @SneakyThrows
    public EventMessage convertMessageToOperationResponse(Message message) {
        return new ObjectMapper().readValue(message.getMsg(), EventMessage.class);
    }

    public String messageReturnOperation(String messageJson) {
        Message message = this.convertJSONtoMessage(messageJson);
        if(message.getMsg().contains("{")) {
            EventMessage eventMessage = this.convertMessageToOperationResponse(message);
            return this.verifyResultOperation(eventMessage, message);
        }
        return message.getMsg();
    }

    public String verifyResultOperation(EventMessage eventMessage, Message message) {
        if(eventMessage.getWin() != null && eventMessage.getWin().equals("win")
           || eventMessage.getResult() != null && eventMessage.getResult().equals("win")
           || eventMessage.getProfitAmount() != null && eventMessage.getProfitAmount().intValue() > 0
           || eventMessage.getWinAmount() != null && eventMessage.getWinAmount().intValue() > 0
           || eventMessage.getWinEnrolledAmount() != null && eventMessage.getWinEnrolledAmount().intValue() > 0) {
            return "Sua operação teve vitória!! Lucro: " + calculateProfitMessage(message, eventMessage);
        } else if(eventMessage.getWin() != null && eventMessage.getWin().equals("loose")
           || eventMessage.getResult() != null && eventMessage.getResult().equals("loose")
           || eventMessage.getProfitAmount() != null && eventMessage.getProfitAmount().intValue() == 0
           || eventMessage.getWinEnrolledAmount() != null && eventMessage.getWinEnrolledAmount().intValue() == 0) {
            return "Sua operação teve derrota!! Lucro: " + BigDecimal.ZERO;
        } else if(eventMessage.getWin() != null && eventMessage.getWin().equals("equal")
                || eventMessage.getResult() != null && eventMessage.getResult().equals("equal")
                || eventMessage.getProfitAmount() != null && eventMessage.getProfitAmount().intValue() == 0
                || eventMessage.getWinEnrolledAmount() != null && eventMessage.getWinEnrolledAmount().intValue() == 0) {
            return "Sua operação teve empate!! Lucro: " + BigDecimal.ZERO;
        }
        return message.getMsg();
    }

    public Double calculateProfit(EventMessage eventMessage) {
        if(eventMessage.getWinEnrolledAmount() != null && eventMessage.getEnrolledAmount() != null) {
            return eventMessage.getWinEnrolledAmount() - eventMessage.getEnrolledAmount();
        }
        if(eventMessage.getProfitAmount() != null && eventMessage.getAmount() != null) {
            return eventMessage.getProfitAmount() - eventMessage.getAmount();
        }
        if(eventMessage.getWinAmount() != null && eventMessage.getAmount() != null) {
            return eventMessage.getWinAmount() - eventMessage.getAmount();
        }
        return 0d;
    }

    public String calculateProfitMessage(Message message, EventMessage eventMessage) {
        return  message.getName().equals("option-archived") ? " " : this.calculateProfit(eventMessage).toString();
    }

}
