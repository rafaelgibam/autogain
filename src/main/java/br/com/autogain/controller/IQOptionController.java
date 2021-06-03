package br.com.autogain.controller;


import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.converter.OperationConverter;
import br.com.autogain.domain.*;
import br.com.autogain.dto.RequestAutoOperation;
import br.com.autogain.repository.ConfigOperationRepository;
import br.com.autogain.repository.OperationRepository;
import br.com.autogain.repository.SignalRepository;
import br.com.autogain.service.IQOptionService;
import br.com.autogain.service.OperationService;
import br.com.autogain.service.SignalService;
import br.com.autogain.service.StrategyMarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/iqoptions")
public class IQOptionController {

    @Autowired
    private IQOption iqOption;
    @Autowired
    private IQOptionService iqOptionService;
    @Autowired
    private SignalRepository signalRepository;
    @Autowired
    private OperationConverter operationConverter;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private ConfigOperationRepository configOperationRepository;
    @Autowired
    private StrategyMarketService strategyMarketService;


    @PostMapping("/connects")
    public ResponseEntity<String> connect(
            @RequestBody User user
            ) {
        iqOption.initUser(user.getEmail(), user.getPassword());
        iqOption.connect();
        iqOption.getAllBinaryData();
        if(iqOption.isAuthenticated()) {
            log.info("connected... already!");
        }
        return ResponseEntity.ok("Connected... already!");
    }

    @GetMapping("/operations/signals")
    public ResponseEntity<List<Signal>> findAll(){
        List<Signal> ListSignal = signalRepository.findAll();
        return ResponseEntity.ok(ListSignal);
    }

    @PostMapping("/operations")
    public ResponseEntity<EventMessage> openOperation(
            @RequestBody Operation operation
            ) {
        EventMessage resultMessage = iqOptionService.openOperation(iqOption, operation);
        return ResponseEntity.ok(resultMessage);
    }

    @PostMapping("/operations/signals")
    public ResponseEntity<Signal> saveOperationSignals(
            @RequestBody Signal signal
    ) {
        return ResponseEntity.ok(signalRepository.save(signal));
    }

    @GetMapping("/operations/signals/{id_signal}")
    public ResponseEntity<Signal> openOperationSignals(@PathVariable("id_signal") String id) {
        Optional<Signal> osignal = signalRepository.findById(id);
        if(osignal.isPresent()) {
            iqOptionService.openOperation(iqOption, osignal.get());
            return ResponseEntity.ok(osignal.get());
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/configurations/{id}/starter")
    public ResponseEntity<Integer> startAutoOperation(@PathVariable String id, @RequestBody RequestAutoOperation requestAutoOperation) {
        Optional<ConfigOperation> configOperation = configOperationRepository.findById(id);
        if(configOperation.isPresent()) {
            return ResponseEntity.ok(strategyMarketService.getCandleBackTrend(requestAutoOperation, configOperation.get()));
        }
        return ResponseEntity.notFound().build();
    }

}
