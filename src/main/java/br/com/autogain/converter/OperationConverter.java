package br.com.autogain.converter;

import br.com.autogain.consumer.iqoption.enums.TimeFrame;
import br.com.autogain.domain.Operation;
import br.com.autogain.domain.Signal;
import br.com.autogain.dto.OperationDTO;
import lombok.SneakyThrows;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class OperationConverter {

    @SneakyThrows
    public Operation convertSignalToOperation(String signal) {
//     M5;AUDCAD;00:30:00;PUT
       String[] operationValues = signal.split(";");

       String today = new SimpleDateFormat("yyyy-mm-dd").format(Calendar.getInstance().getTime());
       Date dateTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(today+" "+operationValues[2]);

       Operation operation =  Operation.builder()
                                        .expiration(TimeFrame.valueOf(operationValues[0]).getId())
                                        .active(operationValues[1])
                                        .entryTime(new DateTime(dateTime))
                                        .direction(operationValues[3])
                                        .build();
       return operation;
    }

    public Operation convertToOperation(OperationDTO operationDTO) {
        Operation operation =  Operation.builder()
                .expiration(operationDTO.getExpiration())
                .active(operationDTO.getActive())
                .direction(operationDTO.getDirection())
                .price(new BigDecimal(10))
                .build();
        return operation;
    }

}
