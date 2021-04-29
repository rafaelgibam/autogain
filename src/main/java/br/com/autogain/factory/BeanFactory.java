package br.com.autogain.factory;

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
