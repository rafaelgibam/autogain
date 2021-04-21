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

    @GetMapping
    public ResponseEntity<List<Operation>> findAll() {
        return ResponseEntity.ok(operationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operation> find(@PathVariable Long id) {
        return ResponseEntity.ok(operationService.getOne(id));
    }

    @PostMapping("/operations")
    public ResponseEntity<String> openOperation(
            @RequestBody Operation operation
    ) {
        String resultMessage = iqOptionService.openOperation(iqOption, operation);
        return ResponseEntity.ok(resultMessage);
    }

}
