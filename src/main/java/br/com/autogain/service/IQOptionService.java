package br.com.autogain.service;

import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.consumer.iqoption.enums.Actives;
import br.com.autogain.consumer.iqoption.enums.BinaryBuyDirection;
import br.com.autogain.consumer.iqoption.enums.TimeFrame;
import br.com.autogain.consumer.iqoption.event.EventListener;
import br.com.autogain.consumer.iqoption.event.Events;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
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

    public EventMessage openOperation(IQOption iqOption, Operation operation) {
        operation.setPrice(BigDecimal.valueOf(10));
        return iqOption.buyBinary(operation.getPrice().doubleValue(),
                BinaryBuyDirection.valueOf(operation.getDirection()),
                Actives.valueOf(operation.getActive()),
                operation.getExpiration());
    }


    public String openOperation(IQOption iqOption, Signal signal) {
        // "M5;AUDCAD;00:30:00;PUT"

        signal.getOperations().stream().forEach(operation -> {
            DateTime entryTimeWithDelay = operation.getEntryTime().minusSeconds(3);
            String entryTimeWithDelayFormat = entryTimeWithDelay.toString("hh:mm:ss");

            while (true) {
                log.info("[API] - Awaiting signal to open operation: " + entryTimeWithDelayFormat +
                        " - Minutes: " +  new DateTime().minusSeconds(1).toString("hh:mm:ss"));

                if(entryTimeWithDelayFormat.equals(new DateTime().minusSeconds(1).toString("hh:mm:ss"))) {
                    log.info("[API] - Opening operation active: ".concat(operation.getActive())
                            .concat(" - Timeframe: ").concat(TimeFrame.get(operation.getExpiration()).toString()));

                    updateOperations(operation, signal);
                    openOperationWS(iqOption, operation);

                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        return this.message;
    }

    private void openAutoOperation(IQOption iqOption, Operation operation) {
        EventMessage eventMessage = eventMessageRepository.findAll(Sort.by(Sort.Direction.ASC, "open_time_millisecond"))
                            .stream().findFirst().get();
        if(eventMessage.getResult().equals("loose") && eventMessage.getDirection().equals("call")) {
            operation.setDirection("put");
            openOperation(iqOption, operation);
        }
        if(eventMessage.getResult().equals("win") && eventMessage.getDirection().equals("call")) {
            operation.setDirection("call");
            openOperation(iqOption, operation);
        }
        if(eventMessage.getResult().equals("equal") && eventMessage.getDirection().equals("call")) {
            operation.setDirection("call");
            openOperation(iqOption, operation);
        }

        if(eventMessage.getResult().equals("loose") && eventMessage.getDirection().equals("put")) {
            operation.setDirection("call");
            openOperation(iqOption, operation);
        }
        if(eventMessage.getResult().equals("win") && eventMessage.getDirection().equals("put")) {
            operation.setDirection("put");
            openOperation(iqOption, operation);
        }
        if(eventMessage.getResult().equals("equal") && eventMessage.getDirection().equals("put")) {
            operation.setDirection("put");
            openOperation(iqOption, operation);
        }
    }


    private void openOperationWS(IQOption iqOption, Operation operation) {
        iqOption.buyBinary(operation.getPrice().doubleValue(),
                BinaryBuyDirection.valueOf(operation.getDirection()),
                Actives.valueOf(operation.getActive()),
                operation.getExpiration());
    }

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
