package br.com.autogain.service;

import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.consumer.iqoption.enums.Actives;
import br.com.autogain.consumer.iqoption.utils.IQUtils;
import br.com.autogain.consumer.iqoption.ws.message.Message;
import br.com.autogain.consumer.iqoption.ws.response.Candle;
import br.com.autogain.converter.MessageConverter;
import br.com.autogain.converter.OperationConverter;
import br.com.autogain.domain.EventMessage;
import br.com.autogain.domain.Operation;
import br.com.autogain.repository.ConfigOperationRepository;
import br.com.autogain.repository.OperationRepository;
import com.ea.async.Async;
import jdk.jfr.Event;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StrategyMarketService {

    @Autowired
    private IQOption iqOption;
    @Autowired
    private IQOptionService iqOptionService;
    @Autowired
    private ConfigOperationRepository configOperationRepository;
    @Autowired
    MessageConverter messageConverter;

    public void BBWithEMA() { }

    @SneakyThrows
    public Candle getCandleBackTrend(Actives actives, int timeFrame) {
        int perdeu = 0;
        log.info("Iniciando operação automática...");
        Candle candle = iqOption.getCandles(1, new DateTime().minus(1), timeFrame, actives).get(0);
        candle.getDirection();
        List<Message> messages = iqOption.getWebSocket().getSyncMsgQueue().stream().filter(v -> v.getName().equals("candles"))
                                                    .collect(Collectors.toList());


        Timestamp stamp = new Timestamp(candle.getAt());
        Timestamp stamp2 = new Timestamp(candle.getTo());
        Timestamp stamp3 = new Timestamp(candle.getFrom());
        Date dateAt = new Date(stamp.getTime());
        Date dateFrom = new Date(stamp2.getTime());
        Date dateTo = new Date(stamp3.getTime());


        BigDecimal stop = BigDecimal.valueOf(20);
        BigDecimal win = BigDecimal.valueOf(20);
        BigDecimal stopAcumulate = BigDecimal.ZERO;
        BigDecimal winAcumulate = BigDecimal.ZERO;

//        while (true) {
//            if(candle.getDirection().name().equals("DOWN")) {
//                log.info("Realizando entrada de VENDA...");
//                Operation operation = Operation.builder()
//                                                .direction("PUT")
//                                                .active(actives.name())
//                                                .expiration(timeFrame)
//                                                .price(BigDecimal.valueOf(5))
//                                               .build();
//                EventMessage eventMessage = iqOptionService.openOperation(iqOption, operation);
//                candle = iqOption.getCandles(1, new DateTime().minus(1), timeFrame, actives).get(0);
//
//                if(eventMessage.getResult().equals("loose")){
//                    log.info("Resultado da operação foi LOSS");
//                    BigDecimal calculateProfit = messageConverter.calculateProfit(eventMessage);
//                    stopAcumulate.add(calculateProfit);
//                }
//                if(eventMessage.getResult().equals("win")) {
//                    log.info("Resultado da operação foi WIN");
//                    BigDecimal calculateProfit = messageConverter.calculateProfit(eventMessage);
//                    stopAcumulate.add(calculateProfit);
//                }
//
//            }
//            if(candle.getDirection().name().equals("UP")){
//                log.info("Realizando entrada de COMPRA...");
//                Operation operation = Operation.builder()
//                                                .direction("CALL")
//                                                .active(actives.name())
//                                                .expiration(timeFrame)
//                                                .price(BigDecimal.valueOf(5))
//                                               .build();
//                EventMessage eventMessage = iqOptionService.openOperation(iqOption, operation);
//                candle = iqOption.getCandles(1, new DateTime().minus(1), timeFrame, actives).get(0);
//
//                if(eventMessage.getResult().equals("loose")){
//                    log.info("Resultado da operação foi LOSS");
//                    BigDecimal calculateProfit = messageConverter.calculateProfit(eventMessage);
//                    stopAcumulate.add(calculateProfit);
//                }
//                if(eventMessage.getResult().equals("win")) {
//                    log.info("Resultado da operação foi WIN");
//                    BigDecimal calculateProfit = messageConverter.calculateProfit(eventMessage);
//                    stopAcumulate.add(calculateProfit);
//                }
//            }
//
//            if(stopAcumulate.equals(stop)) {
//                log.info("Infelizmente você bateu seu stop tente novamente amanhã.");
//                break;
//            }
//            if(winAcumulate.equals(win)) {
//                log.info("Parabéns você bateu seu stop win.");
//                break;
//            }
//        }
        return candle;
    }

    public void MHI() {

    }

    public void supportResistence() {

    }


}
