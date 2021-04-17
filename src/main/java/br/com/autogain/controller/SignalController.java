package br.com.autogain.controller;

import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.domain.Operation;
import br.com.autogain.domain.Signal;
import br.com.autogain.service.OperationService;
import br.com.autogain.service.SignalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/signals")
public class SignalController {

    @Autowired
    IQOption iqOption;
    @Autowired
    private SignalService signalService;
    @Autowired
    private OperationService operationService;



    @GetMapping
    public boolean findAll() {
        return iqOption.isAuthenticated();
    }



    @PostMapping("signals/{id}/operations")
    public ResponseEntity<List<Operation>> operationsList(@PathVariable Long id, @RequestBody List<Operation> operations){

        Signal signal = signalService.getOne(id);

        operations = operations.stream().map(operation -> {
            operation.setSignal(signal);
            return operation;
        }).collect(Collectors.toList());

        operations.stream().forEach(operation -> operationService.save(operation));

        return ResponseEntity.ok(operations);
    }







}
