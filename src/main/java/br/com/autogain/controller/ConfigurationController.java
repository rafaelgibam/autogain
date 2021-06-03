package br.com.autogain.controller;

import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.consumer.iqoption.enums.Actives;
import br.com.autogain.consumer.iqoption.ws.message.Message;
import br.com.autogain.consumer.iqoption.ws.response.Candle;
import br.com.autogain.domain.ConfigOperation;
import br.com.autogain.domain.Operation;
import br.com.autogain.dto.RequestAutoOperation;
import br.com.autogain.repository.ConfigOperationRepository;
import br.com.autogain.repository.OperationRepository;
import br.com.autogain.service.IQOptionService;
import br.com.autogain.service.StrategyMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/configurations")
public class ConfigurationController {

    @Autowired
    private IQOption iqOption;
    @Autowired
    private ConfigOperationRepository configOperationRepository;
    @Autowired
    private IQOptionService iqOptionService;
    @Autowired
    private StrategyMarketService strategyMarketService;

    @GetMapping
    public ResponseEntity<List<ConfigOperation>> getConfigurations() {
        Optional<List<ConfigOperation>> oConfigOperations = Optional.of(configOperationRepository.findAll());
        if(oConfigOperations.isPresent()) {
            return ResponseEntity.ok(oConfigOperations.get());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ConfigOperation>> getIdConfigurations(@PathVariable String id){
        Optional<ConfigOperation> idConfigOperations = configOperationRepository.findById(id);
        return ResponseEntity.ok(idConfigOperations);
    }


    @PostMapping
    public ResponseEntity<ConfigOperation> saveConfiguration(@RequestBody ConfigOperation configOperation) {
        return ResponseEntity.ok(configOperationRepository.save(configOperation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfigOperation> updateConfiguration(@PathVariable String id, @RequestBody ConfigOperation configOperation) {
        configOperation.setId(id);
        configOperationRepository.save(configOperation);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ConfigOperation> deleteConfiguration(@PathVariable String id) {
        configOperationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}