package br.com.autogain.controller;

import br.com.autogain.consumer.iqoption.IQOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signals")
public class SignalController {

    @Autowired
    IQOption iqOption;

    @GetMapping
    public boolean findAll() {
        return iqOption.isAuthenticated();
    }
}
