package br.com.autogain.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class ManagementOperationService {

    public BigInteger calculateMartinGale(Double loose, Integer payout) {
        // Martingale: valor_proxima_entrada = valor_loss + ((100 - payout) * 2)
        Double calculatedMartinGale = (Math.ceil(loose + (loose * ((100 - payout) * 2))/100));
        return BigInteger.valueOf(calculatedMartinGale.intValue());
    }

    public BigInteger calculateSoros(Double price, Integer payout) {
        // valor_proxima_entrada = valor_entrada + ((valor_entrada * payout)/100)
        Double calculatedSoros = Math.ceil(price + ((price * payout)/100));
        return  BigInteger.valueOf(calculatedSoros.intValue());
    }

    public BigDecimal calculateSorosGale(Double loose, Integer payout) {
        // SorosGale: valor_proxima_entrada = caso loss -> soros((martingale / 2)) || caso win soros(valor_entrada)
        Double valueCalculed = this.calculateMartinGale(loose, payout).doubleValue() / 2;
        return BigDecimal.valueOf(valueCalculed);
    }
}
