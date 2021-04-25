package br.com.autogain.controller;

import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.domain.Operation;
import br.com.autogain.dto.OperationDTO;
import br.com.autogain.service.IQOptionService;
import br.com.autogain.service.OperationService;
import br.com.autogain.service.StrategyMarketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("/operations")
@RestController
@RequestMapping("/operations")
public class OperationController {

    @Autowired
    private IQOption iqOption;
    @Autowired
    private StrategyMarketService strategyMarketService;
    @Autowired
    private OperationService operationService;
    @Autowired
    private IQOptionService iqOptionService;

    @GetMapping
    @ApiOperation(value = "Listar todas as operações")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = OperationDTO.class)
    })
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

    @PutMapping("/{id}")
    public ResponseEntity<Operation> updateOperations(@RequestBody Operation operation, @PathVariable Long id) {
         operation.setId(id);
         return ResponseEntity.ok(operationService.save(operation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Operation> deleteOPeration(@PathVariable Long id){
        operationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    public void test() throws InterruptedException {
        strategyMarketService.getCandleBackTrend();
    }

}
