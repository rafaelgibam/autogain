package br.com.autogain.controller;


import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.consumer.iqoption.enums.Actives;
import br.com.autogain.consumer.iqoption.enums.BalanceType;
import br.com.autogain.consumer.iqoption.enums.BinaryBuyDirection;
import br.com.autogain.consumer.iqoption.event.Events;
import br.com.autogain.consumer.iqoption.rest.request.LoginRequest;
import br.com.autogain.consumer.iqoption.utils.IQUtils;
import br.com.autogain.consumer.iqoption.ws.request.BinaryBuyRequest;
import br.com.autogain.model.Operation;
import br.com.autogain.model.User;
import br.com.autogain.service.IQOptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/iqoptions")
public class IQOptionController {

    private IQOption iqOption;

    @Autowired
    private IQOptionService iqOptionService;

    @PostMapping("/connects")
    public ResponseEntity<String> connect(
            @RequestBody User user
            ) {
        iqOption = new IQOption(user.getEmail(), user.getPassword());
        iqOption.connect();
        iqOption.getAllBinaryData();
        if(iqOption.isAuthenticated()) {
            log.info("connected... already!");
        }
        return ResponseEntity.ok("Connected... already!");
    }

    @PostMapping("/operations")
    public ResponseEntity<String> openOperation(
            @RequestBody Operation operation
            ) {
        String resultMessage = iqOptionService.openOperation(iqOption, operation);
        return ResponseEntity.ok(resultMessage);
    }

    @PostMapping("/operations/signals")
    public ResponseEntity<String> openOperationSignals(
            @RequestBody List<String> listSignals
    ) {
        iqOptionService.openOperation(iqOption, listSignals);
        return ResponseEntity.ok("");
    }

    @GetMapping("/autos")
    public ResponseEntity<String> autoOperation(
            @RequestBody Operation operation
    ) {
        iqOptionService.openOperation(iqOption, operation);
        return ResponseEntity.ok("");
    }

}
