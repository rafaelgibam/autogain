package br.com.autogain.service;


import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.consumer.iqoption.enums.Actives;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StrategyMarketService {

    @Autowired
    private IQOption iqOption;

    public void BBWithEMA() { }
    public void getCandleBackTrend() {
        iqOption.getCandles(1, 1, 60, Actives.EURUSD_OTC);
    }
    public void MHI() {

    }

    public void supportResistence() {

    }

}
