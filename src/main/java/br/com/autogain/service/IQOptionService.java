package br.com.autogain.service;

import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.consumer.iqoption.enums.Actives;
import br.com.autogain.consumer.iqoption.enums.BinaryBuyDirection;
import br.com.autogain.consumer.iqoption.enums.TimeFrame;
import br.com.autogain.consumer.iqoption.event.EventListener;
import br.com.autogain.consumer.iqoption.event.Events;
import br.com.autogain.converter.OperationConverter;
import br.com.autogain.dto.OperationDTO;
import br.com.autogain.model.EventMessage;
import br.com.autogain.model.Operation;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class IQOptionService implements EventListener {

    private String message;
    @Autowired
    private OperationConverter converter;
    @Autowired
    private EventMessageService eventMessageService;

    public String openOperation(IQOption iqOption, Operation operation) {
        iqOption.buyBinary(operation.getPrice(),
                BinaryBuyDirection.valueOf(operation.getDirection()),
                Actives.valueOf(operation.getActive()),
                operation.getExpiration());
        return this.message;
    }


    public String openOperation(IQOption iqOption, List<String> listSignals) {
        // "M5;AUDCAD;00:30:00;PUT"

        listSignals.stream().forEach(ls -> {
            OperationDTO operationDTO = converter.convertSignalToOperation(ls);
            DateTime entryTimeWithDelay = operationDTO.getEntryTime().minusSeconds(3);
            String entryTimeWithDelayFormat = entryTimeWithDelay.toString("mm:ss");

            while (true) {
                log.info("[API] - Awaiting signal to open operation: " + entryTimeWithDelayFormat +
                        " - Minutes: " +  new DateTime().minusSeconds(1).toString("mm:ss"));

                if(entryTimeWithDelayFormat.equals(new DateTime().minusSeconds(1).toString("mm:ss"))) {
                    log.info("[API] - Opening operation active: ".concat(operationDTO.getActive())
                            .concat(" - Timeframe: ").concat(TimeFrame.get(operationDTO.getExpiration()).toString()));
                    openOperationWS(iqOption, converter.convertToOperation(operationDTO));
                    break;
                }
            }
        });

        return this.message;
    }

    private void openAutoOperation(IQOption iqOption, Operation operation) {
        EventMessage eventMessage = eventMessageService.findAll(Sort.by(Sort.Direction.ASC, "open_time_millisecond"))
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
        iqOption.buyBinary(operation.getPrice(),
                BinaryBuyDirection.valueOf(operation.getDirection()),
                Actives.valueOf(operation.getActive()),
                operation.getExpiration());
    }

    @Override
    public void update(Events ev, String message) {
        log.info("Capturando message no service: ".concat(message).concat(" " + ev.toString()));
        this.message = message;
    }
}
