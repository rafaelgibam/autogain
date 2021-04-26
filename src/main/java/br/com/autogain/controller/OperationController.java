package br.com.autogain.controller;

import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.domain.Operation;
import br.com.autogain.service.IQOptionService;
import br.com.autogain.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationController {

    @Autowired
    private IQOption iqOption;
    @Autowired
    private OperationService operationService;
    @Autowired
    private IQOptionService iqOptionService;

    @GetMapping()
    public ResponseEntity<List<Operation>> findAll() {
        return ResponseEntity.ok(operationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operation> find(@PathVariable Long id) {
        return ResponseEntity.ok(operationService.getOne(id));
    }

    @PostMapping()
    public ResponseEntity<Operation> saveOperation(@RequestBody Operation operation){

    Operation operations = operationService.save(operation);

        return  ResponseEntity.ok(operations);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Operation> updateOperations(@RequestBody Operation operation, @PathVariable Long id){

     operation.setId(id);

        return ResponseEntity.ok(operationService.save(operation));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Operation> deleteOPeration(@PathVariable Long id){

        operationService.delete(id);

        return ResponseEntity.noContent().build();
    }
}