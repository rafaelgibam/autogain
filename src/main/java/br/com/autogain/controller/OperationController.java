package br.com.autogain.controller;

import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.consumer.iqoption.enums.Actives;
import br.com.autogain.consumer.iqoption.ws.message.Message;
import br.com.autogain.consumer.iqoption.ws.response.Candle;
import br.com.autogain.domain.ConfigOperation;
import br.com.autogain.domain.Operation;
import br.com.autogain.repository.ConfigOperationRepository;
import br.com.autogain.repository.OperationRepository;
import br.com.autogain.service.IQOptionService;
import br.com.autogain.service.StrategyMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/operations")
public class OperationController {

    @Autowired
    private IQOption iqOption;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private ConfigOperationRepository configOperationRepository;
    @Autowired
    private IQOptionService iqOptionService;
    @Autowired
    private StrategyMarketService strategyMarketService;

    @GetMapping
    public ResponseEntity<List<Operation>> findAll() {
        return ResponseEntity.ok(operationRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operation> find(@PathVariable String id) {
        Optional<Operation> ooperation = operationRepository.findById(id);
        if(ooperation.isPresent()) {
            return ResponseEntity.ok(ooperation.get());
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Operation> saveOperation(@RequestBody Operation operation){
        Operation operations = operationRepository.save(operation);
        return  ResponseEntity.ok(operations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operation> updateOperations(@RequestBody Operation operation, @PathVariable String id){
        operation.setId(id);
        return ResponseEntity.ok(operationRepository.save(operation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Operation> deleteOPeration(@PathVariable String id){
        operationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/configs")
    public ResponseEntity<List<ConfigOperation>> getConfigurations() {
        Optional<List<ConfigOperation>> oConfigOperations = Optional.of(configOperationRepository.findAll());
        if(oConfigOperations.isPresent()) {
            return ResponseEntity.ok(oConfigOperations.get());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/configs/{id}")
    public ResponseEntity<Optional<ConfigOperation>> getIdConfigurations(@PathVariable String id){

        Optional<ConfigOperation> idConfigOperations = configOperationRepository.findById(id);

        return ResponseEntity.ok(idConfigOperations);
        }


    @PostMapping("/configs")
    public ResponseEntity<ConfigOperation> saveConfiguration(@RequestBody ConfigOperation configOperation) {
        return ResponseEntity.ok(configOperationRepository.save(configOperation));
    }

    @PutMapping("/configs/{id}")
    public ResponseEntity<ConfigOperation> updateConfiguration(@PathVariable String id, @RequestBody ConfigOperation configOperation) {
        configOperation.setId(id);
        configOperationRepository.save(configOperation);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/configs/{id}")
    public ResponseEntity<ConfigOperation> deleteConfiguration(@PathVariable String id) {
        configOperationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/start")
    public ResponseEntity<Integer> startAutoOperation() {
        return ResponseEntity.ok(strategyMarketService.getCandleBackTrend(Actives.EURUSD, 1));
//        return ResponseEntity.ok("Auto Operação Iniciada com Sucesso!");
    }



}