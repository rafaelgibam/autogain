package br.com.autogain.factory;

import br.com.autogain.consumer.iqoption.event.EventManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class BeanFactory {

    @Bean
    public HttpHeaders headers() {
        return new HttpHeaders();
    }



}
