package br.com.autogain.service;


import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.consumer.iqoption.enums.Actives;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class StrategyMarketService {

    @Autowired
    private IQOption iqOption;

    public void BBWithEMA() { }

    public String getCandleBackTrend() {
       return iqOption.getCandles(1, 1, 60, Actives.EURUSD);
    }

    public void MHI() {

    }

    public void supportResistence() {

    }

}
