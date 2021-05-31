package br.com.autogain.service;

import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.consumer.iqoption.enums.Actives;
import br.com.autogain.consumer.iqoption.enums.BinaryBuyDirection;
import br.com.autogain.consumer.iqoption.enums.TimeFrame;
import br.com.autogain.consumer.iqoption.event.EventListener;
import br.com.autogain.consumer.iqoption.event.Events;
import br.com.autogain.converter.MessageConverter;
import br.com.autogain.converter.OperationConverter;
import br.com.autogain.domain.EventMessage;
import br.com.autogain.domain.Operation;
import br.com.autogain.domain.Signal;
import br.com.autogain.repository.EventMessageRepository;
import br.com.autogain.repository.OperationRepository;
import br.com.autogain.repository.SignalRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class IQOptionService implements EventListener {

    private String message;
    @Autowired
    private OperationConverter converter;
    @Autowired
    private EventMessageRepository eventMessageRepository;
    @Autowired
    private SignalRepository signalRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private MessageConverter messageConverter;

    public EventMessage openOperation(IQOption iqOption, Operation operation) {
        return iqOption.buyBinary(operation.getPrice().doubleValue(),
                BinaryBuyDirection.valueOf(operation.getDirection()),
                Actives.valueOf(operation.getActive()),
                operation.getExpiration());
    }


    public String openOperation(IQOption iqOption, Signal signal) {
        // "M5;AUDCAD;00:30:00;PUT"
        Double take = 0d;
        Double stop = 0d;

        List<Operation> operations
                = signal.getOperations().stream().sorted(Comparator.comparing(Operation::getEntryTime))
                  .filter(operation -> !operation.getStatus())
                  .collect(Collectors.toList());

        if(!operations.isEmpty()){

         for(Operation operation : operations){
            DateTime entryTimeWithDelay = operation.getEntryTime().minusSeconds(3);
            String entryTimeWithDelayFormat = entryTimeWithDelay.toString("HH:mm:ss");

            if(take.doubleValue() >= signal.getConfigOperation().getTake().doubleValue()){

                log.info("Parabéns você bateu a meta!");
                break;
            }
            if(stop.doubleValue() >= signal.getConfigOperation().getStop().doubleValue()){
                log.info("Você foi Stopado");
                break;
            }
            while (true) {
                log.info("[API] - Awaiting signal to open operation: " + entryTimeWithDelayFormat +
                        " - Minutes: " +  new DateTime().minusSeconds(1).toString("HH:mm:ss"));

                if(entryTimeWithDelayFormat.equals(new DateTime().minusSeconds(1).toString("HH:mm:ss"))) {
                    log.info("[API] - Opening operation active: ".concat(operation.getActive())
                            .concat(" - Timeframe: ").concat(TimeFrame.get(operation.getExpiration()).toString()));

                    updateOperations(operation, signal);
                    EventMessage eventMessage = openOperation(iqOption, operation);

                    if(eventMessage.getResult().equals("win")){
                          take = take + messageConverter.calculateProfit(eventMessage);
                    }
                    if(eventMessage.getResult().equals("loose")){
                          stop = stop + signal.getConfigOperation().getPrice();
                    }
                    break;
                }
                try {

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        }

        return this.message;
    }

    public void openAutoOperation(IQOption iqOption, Operation operation, EventMessage eventMessage) {
        Double stop = 0d;
        EventMessage eventMessageRecursive = eventMessage;

        while (true) {
            if(eventMessageRecursive.getResult().equals("loose") && eventMessageRecursive.getDirection().equals("call")) {
                operation.setDirection("PUT");
                eventMessageRecursive = openOperation(iqOption, operation);
                stop = stop - messageConverter.calculateProfit(eventMessageRecursive);
            }
            if(eventMessageRecursive.getResult().equals("win") && eventMessageRecursive.getDirection().equals("call")) {
                operation.setDirection("CALL");
                eventMessageRecursive = openOperation(iqOption, operation);
            }
            if(eventMessageRecursive.getResult().equals("equal") && eventMessageRecursive.getDirection().equals("call")) {
                operation.setDirection("CALL");
                eventMessageRecursive = openOperation(iqOption, operation);
            }
            if(eventMessageRecursive.getResult().equals("loose") && eventMessageRecursive.getDirection().equals("put")) {
                operation.setDirection("CALL");
                eventMessageRecursive = openOperation(iqOption, operation);
                stop = stop + messageConverter.calculateProfit(eventMessageRecursive);
            }
            if(eventMessageRecursive.getResult().equals("win") && eventMessageRecursive.getDirection().equals("put")) {
                operation.setDirection("PUT");
                eventMessageRecursive = openOperation(iqOption, operation);
            }
            if(eventMessageRecursive.getResult().equals("equal") && eventMessageRecursive.getDirection().equals("put")) {
                operation.setDirection("PUT");
                eventMessageRecursive = openOperation(iqOption, operation);
            }
            if(eventMessageRecursive != null) {
                if(stop.equals(20)) {
                    break;
                } else {
                    openAutoOperation(iqOption, operation, eventMessageRecursive);
                }
            }
        }
    }


//    public void openAutoOperation(IQOption iqOption, Operation operation, EventMessage eventMessage) {
//        BigDecimal stop = BigDecimal.ZERO;
//        EventMessage eventMessageRecursive = eventMessage;
//
//        while (true) {
//            eventMessageRecursive = openOperation(iqOption, operation);
//            if(iqOption.verifyResult(eventMessageRecursive)) {
//
//            }
//            stop.add(messageConverter.calculateProfit(eventMessageRecursive));
//            break;
//        }
//
//        if(!stop.equals(20)) {
//            if(eventMessageRecursive.getResult().equals("loose") && eventMessageRecursive.getDirection().equals("call")) {
//                operation.setDirection("PUT");
//                openAutoOperation(iqOption, operation, eventMessageRecursive);
//            }
//            if(eventMessageRecursive.getResult().equals("win") && eventMessageRecursive.getDirection().equals("call")) {
//                operation.setDirection("CALL");
//                openAutoOperation(iqOption, operation, eventMessageRecursive);
//            }
//            if(eventMessageRecursive.getResult().equals("equal") && eventMessageRecursive.getDirection().equals("call")) {
//                operation.setDirection("CALL");
//                openAutoOperation(iqOption, operation, eventMessageRecursive);
//            }
//            if(eventMessageRecursive.getResult().equals("loose") && eventMessageRecursive.getDirection().equals("put")) {
//                operation.setDirection("CALL");
//                openAutoOperation(iqOption, operation, eventMessageRecursive);
//            }
//            if(eventMessageRecursive.getResult().equals("win") && eventMessageRecursive.getDirection().equals("put")) {
//                operation.setDirection("PUT");
//                openAutoOperation(iqOption, operation, eventMessageRecursive);
//            }
//            if(eventMessageRecursive.getResult().equals("equal") && eventMessageRecursive.getDirection().equals("put")) {
//                operation.setDirection("PUT");
//                openAutoOperation(iqOption, operation, eventMessageRecursive);
//            }
//        }
//    }

    private void updateOperations(Operation operation, Signal signal){

        operation.setStatus(true);
        operationRepository.save(operation);

        List<Operation> listOperations =  signal.getOperations().stream().map(operation1 -> {
            if(operation1.getId().equals(operation.getId())){
                operation1.setStatus(true);
                return operation1;
            }
            return operation1;
        }).collect(Collectors.toList());
        signal.setOperations(listOperations);
        signalRepository.save(signal);
        operation.setPrice(signal.getConfigOperation().getPrice());
    }

    @Override
    public void update(Events ev, String message) {
        log.info("Capturando message no service: ".concat(message).concat(" " + ev.toString()));
        this.message = message;
    }
}
