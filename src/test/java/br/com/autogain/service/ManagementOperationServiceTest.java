package br.com.autogain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.BigInteger;

@SpringBootTest
@AutoConfigureMockMvc
public class ManagementOperationServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ManagementOperationService service;

    @Test
    public void calculedMartingaleTest() {
        Double price = 10.0;
        Integer payout = 87;
        BigInteger valueCalculed = service.calculateMartinGale(price, payout);
        Assertions.assertEquals(valueCalculed.intValue(), 13);
    }

    @Test
    public void calculedSorosTest() {
        Double price = 10.0;
        Integer payout = 87;
        BigInteger valueCalculed = service.calculateSoros(price, payout);
        Assertions.assertEquals(valueCalculed.intValue(), 19);
    }

    @Test
    public void calculedSorosGaleTest() {
        Double price = 10.0;
        Integer payout = 87;
        BigDecimal valueCalculed = service.calculateSorosGale(price, payout);
        Assertions.assertEquals(valueCalculed.doubleValue(), 6.5);
    }

    @Test
    public void calculedSorosGaleTestSecondWithWin() {
        Double price = 23.0;
        Integer payout = 87;
        BigDecimal valueCalculed = service.calculateSorosGale(price, payout);
        Assertions.assertEquals(valueCalculed.doubleValue(), 14.5);
    }

}
