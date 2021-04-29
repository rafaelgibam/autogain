package br.com.autogain.service;


import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.consumer.iqoption.enums.Actives;
import br.com.autogain.consumer.iqoption.ws.message.Message;
import br.com.autogain.consumer.iqoption.ws.response.Candle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class StrategyMarketService {

    @Autowired
    private IQOption iqOption;

    public void BBWithEMA() { }

    public List<Candle> getCandleBackTrend() {
        List<Candle> candles = iqOption.getCandles(3, new Date().getTime(), 60, Actives.AUDJPY);
        if(candles == null) {
            candles = iqOption.getCandles(3, new Date().getTime(), 60, Actives.AUDJPY);
        }
        return candles;
    }

    public void MHI() {

    }

    public void supportResistence() {

    }

}
