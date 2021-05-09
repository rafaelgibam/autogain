package br.com.autogain.service;

import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.consumer.iqoption.enums.Actives;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class StrategyMarketServiceTest {
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StrategyMarketService service;

    @Autowired
    private IQOption iqOption;

    @BeforeEach
    public void init() {
        iqOption.initUser("geekcife@gmail.com", "klb8293pa");
        iqOption.connect();
    }

    @Test
    public void getCandleBackTrend() {
        iqOption.getCandles(1, new DateTime(), 60, Actives.EURUSD_OTC);

    }

    @Test
    public void BBWithEMA() { }

    @Test
    public void MHI() {
    }

    @Test
    public void supportResistence() {

    }
}
