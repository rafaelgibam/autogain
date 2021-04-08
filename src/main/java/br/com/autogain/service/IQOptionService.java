package br.com.autogain.service;

import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.consumer.iqoption.enums.Actives;
import br.com.autogain.consumer.iqoption.enums.BinaryBuyDirection;
import br.com.autogain.consumer.iqoption.enums.TimeFrame;
import br.com.autogain.consumer.iqoption.event.EventListener;
import br.com.autogain.consumer.iqoption.event.Events;
import br.com.autogain.consumer.iqoption.service.BaseService;
import br.com.autogain.converter.OperationConverter;
import br.com.autogain.dto.OperationDTO;
import br.com.autogain.model.Operation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class IQOptionService implements EventListener {

    private String message;
    @Autowired
    private OperationConverter converter;

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
            while (!operationDTO.getEntryTime().equals(LocalDate.now())) {
                log.info("[API] - Awaiting time signal to open!");
                if(operationDTO.getEntryTime().equals(LocalDate.now())) {
                    log.info("[API] - Opening operation active: ".concat(operationDTO.getActive())
                            .concat("Timeframe: ").concat(TimeFrame.get(operationDTO.getExpiration()).toString()));
                    openOperationWS(iqOption, converter.convertToOperation(operationDTO));
                }
            }
        });

        return this.message;
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
