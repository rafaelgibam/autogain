package br.com.autogain.controller;


import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.converter.OperationConverter;
import br.com.autogain.domain.Operation;
import br.com.autogain.domain.Signal;
import br.com.autogain.domain.User;
import br.com.autogain.dto.SignalDTO;
import br.com.autogain.service.IQOptionService;
import br.com.autogain.service.SignalService;
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
    @Autowired
    private SignalService signalService;
    @Autowired
    private OperationConverter operationConverter;

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

    @GetMapping("/operations/signals")
    public ResponseEntity<List<Signal>> findAll(){

        List<Signal> ListSignal = signalService.findAll();

        return ResponseEntity.ok(ListSignal);
    }


    @PostMapping("/operations")
    public ResponseEntity<String> openOperation(
            @RequestBody Operation operation
            ) {
        String resultMessage = iqOptionService.openOperation(iqOption, operation);
        return ResponseEntity.ok(resultMessage);
    }

    @PostMapping("/operations/signals")
    public ResponseEntity<Signal> saveOperationSignals(
            @RequestBody Signal signal
    ) {
        return ResponseEntity.ok(signalService.save(signal));
    }

    @GetMapping("/operations/signals/{id_signal}")
    public ResponseEntity<String> openOperationSignals(@PathVariable("id_signal") Long id) {
        Signal signal = signalService.getOne(id);
        iqOptionService.openOperation(iqOption, signal);
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
