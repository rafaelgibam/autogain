package br.com.autogain.converter;

import br.com.autogain.consumer.iqoption.enums.TimeFrame;
import br.com.autogain.dto.OperationDTO;
import br.com.autogain.model.Operation;
import lombok.SneakyThrows;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

@Component
public class OperationConverter {

    @SneakyThrows
    public OperationDTO convertSignalToOperation(String signal) {
//     M5;AUDCAD;00:30:00;PUT
       String[] operationValues = signal.split(";");

        String dateEntryFormatted = LocalDateTime.parse(operationValues[2]).format(DateTimeFormatter.ofPattern("hh:mm:ss"));
        LocalDate dateEntry = LocalDate.parse(dateEntryFormatted);

        OperationDTO operationDTO =  OperationDTO.builder()
                                        .expiration(TimeFrame.valueOf(operationValues[0]).getId())
                                        .active(operationValues[1])
                                        .entryTime(dateEntry)
                                        .direction(operationValues[3])
                                        .build();
       return operationDTO;
    }

    public Operation convertToOperation(OperationDTO operationDTO) {
        Operation operation =  Operation.builder()
                .expiration(operationDTO.getExpiration())
                .active(operationDTO.getActive())
                .direction(operationDTO.getDirection())
                .price(10.0)
                .build();
        return operation;
    }

}
