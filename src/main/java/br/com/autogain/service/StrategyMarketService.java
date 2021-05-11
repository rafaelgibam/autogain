package br.com.autogain.service;

import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.consumer.iqoption.enums.Actives;
import br.com.autogain.consumer.iqoption.ws.response.Candle;
import br.com.autogain.domain.ConfigOperation;
import br.com.autogain.domain.EventMessage;
import br.com.autogain.domain.Operation;
import br.com.autogain.dto.RequestAutoOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
@Service
public class StrategyMarketService {

    @Autowired
    private IQOption iqOption;
    @Autowired
    private IQOptionService iqOptionService;

    public void BBWithEMA() { }

    @SneakyThrows
    public int getCandleBackTrend(RequestAutoOperation requestAutoOperation, ConfigOperation configOperation) {
        BigDecimal stop = BigDecimal.ZERO;
        Candle candle = iqOption.getCandles(1, new Date().getTime(), requestAutoOperation.getExpiration(), Actives.valueOf(requestAutoOperation.getActive())).get(0);
        log.info("Iniciando operação automática...");
        Operation operation = Operation.builder()
                .direction(candle.getDirection().name().equals("UP") ? "CALL" : "PUT")
                .active(requestAutoOperation.getActive())
                .expiration(requestAutoOperation.getExpiration())
                .price(configOperation.getPrice())
                .build();

        EventMessage eventMessage = iqOptionService.openOperation(iqOption, operation);
        if(eventMessage.getResult() != null) {
            iqOptionService.openAutoOperation(iqOption, operation, eventMessage);
        }

        return 1;
    }

    public void MHI() {

    }

    public void supportResistence() {

    }


}
