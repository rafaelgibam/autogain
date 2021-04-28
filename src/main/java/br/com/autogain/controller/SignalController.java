package br.com.autogain.controller;
import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.domain.Operation;
import br.com.autogain.domain.Signal;
import br.com.autogain.repository.OperationRepository;
import br.com.autogain.repository.SignalRepository;
import br.com.autogain.service.OperationService;
import br.com.autogain.service.SignalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/signals")
public class SignalController {

    @Autowired
    IQOption iqOption;
    @Autowired
    private SignalRepository signalRepository;
    @Autowired
    private OperationRepository operationRepository;


    @GetMapping
    public ResponseEntity<List<Signal>> listSignals(){

       List<Signal> signalList = signalRepository.findAll();

      return ResponseEntity.ok(signalList);
    }

    @PostMapping
    public ResponseEntity<Signal> saveSignal(@RequestBody Signal signal){

        Signal signals = signalRepository.save(signal);

        return ResponseEntity.ok(signals);
    }
    @GetMapping("/{id}/operations")
    public ResponseEntity<List<Operation>> signalsOperationsList(@PathVariable String id){

        Optional<Signal> osignals = signalRepository.findById(id);
        if(osignals.isPresent()) {
            return ResponseEntity.ok(osignals.get().getOperations());
        }
        return  ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/operations")
    public ResponseEntity<Signal> operationsList(@PathVariable String id, @RequestBody List<Operation> operations){

        Optional<Signal> osignal = signalRepository.findById(id);
        if(osignal.isPresent()) {
            osignal.get().setOperations(operations);
            signalRepository.save(osignal.get());
            return ResponseEntity.ok(osignal.get());
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Signal> UpdateSignals(@RequestBody Signal signal, @PathVariable Long id){
        signal.setId(id);
        return ResponseEntity.ok(signalRepository.save(signal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSignals(@PathVariable String id){
        signalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
