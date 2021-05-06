package br.com.autogain.service;

import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.consumer.iqoption.enums.Actives;
import br.com.autogain.consumer.iqoption.utils.IQUtils;
import br.com.autogain.consumer.iqoption.ws.response.Candle;
import br.com.autogain.converter.OperationConverter;
import br.com.autogain.domain.Operation;
import br.com.autogain.repository.OperationRepository;
import com.ea.async.Async;
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
    public int getCandleBackTrend(Actives actives, int timeFrame) {
        int perdeu = 0;

        log.info("Iniciando operação automática...");
        while (true) {
            Candle candle = iqOption.getCandles(1, new Date().getTime(), timeFrame, actives).get(0);
            if(candle.getDirection().name().equals("DOWN")) {
                log.info("Realizando entrada de VENDA...");
                Operation operation = Operation.builder()
                                                .direction("PUT")
                                                .active(actives.name())
                                                .expiration(timeFrame)
                                                .price(BigDecimal.valueOf(5))
                                               .build();
                iqOptionService.openOperation(iqOption, operation);
                if(iqOption.getResultOperation().getResult().equals("loose")){
                    log.info("Resultado da operação foi LOSS");
                    perdeu++;
                }
            }
            if(candle.getDirection().name().equals("UP")){
                log.info("Realizando entrada de COMPRA...");
                Operation operation = Operation.builder()
                        .direction("CALL")
                        .active(actives.name())
                        .expiration(timeFrame)
                        .price(BigDecimal.valueOf(5))
                        .build();
                iqOptionService.openOperation(iqOption, operation);
                if(iqOption.getResultOperation().getResult().equals("loose")){
                    log.info("Resultado da operação foi LOSS");
                    perdeu++;
                }
            }

            if(perdeu == 10) {
                break;
            }
        }
        return perdeu;
    }

    public void MHI() {

    }

    public void supportResistence() {

    }


}
