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
    public ResponseEntity<List<Operation>> signalsOperationsList(@PathVariable Long id){

        Signal signals = signalService.getOne(id);
        return  ResponseEntity.ok(signals.getOperations());
    }

    @PostMapping("/{id}/operations")
    public ResponseEntity<Signal> operationsList(@PathVariable Long id, @RequestBody List<Operation> operations){

        Signal signal = signalService.getOne(id);

        signal.setOperations(operations);

        signalService.save(signal);

        return ResponseEntity.ok(signal);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Signal> UpdateSignals(@RequestBody Signal signal, @PathVariable Long id){

        signal.setId(id);

      return ResponseEntity.ok(signalService.save(signal));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSignals(@PathVariable Long id){

        signalService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
