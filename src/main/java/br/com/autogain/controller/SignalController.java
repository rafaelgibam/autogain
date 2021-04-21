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


    @GetMapping()
    public ResponseEntity<List<Signal>> listSignals(){

       List<Signal> signalList = signalService.findAll();

      return ResponseEntity.ok(signalList);
    }

    @PostMapping()
    public ResponseEntity<Signal> saveSignal(@RequestBody Signal signal){

        Signal signals = signalService.save(signal);

        return ResponseEntity.ok(signals);
    }

    @GetMapping("/{id}/operations")
    public ResponseEntity<List<Operation>> signalsOperationsList(@PathVariable Long id, @RequestBody List<Operation> operations){

        Signal signals = signalService.getOne(id);

        operations = operations.stream().map(operation -> {
            operation.setSignal(signals);
            return operation;}).collect(Collectors.toList());

            return  ResponseEntity.ok(operations);
        }



    @PostMapping("/{id}/operations")
    public ResponseEntity<List<Operation>> operationsList(@PathVariable Long id, @RequestBody List<Operation> operations){

        Signal signal = signalService.getOne(id);

        operations = operations.stream().map(operation -> {
            operation.setSignal(signal);
            return operation;
        }).collect(Collectors.toList());

        operations.stream().forEach(operation -> operationService.save(operation));

        return ResponseEntity.ok(operations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Signal> UpdateSignals(@RequestBody Signal signal, @PathVariable Long id){

        signal.setId(id);


      return ResponseEntity.ok(signalService.save(signal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletSignals(@RequestBody Signal signal, @PathVariable Long id){


        signalService.deleteAllInBatch();


        return ResponseEntity.noContent().build();
    }





}
