package br.com.autogain.controller;
import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.domain.ConfigOperation;
import br.com.autogain.domain.Operation;
import br.com.autogain.domain.Signal;
import br.com.autogain.repository.ConfigOperationRepository;
import br.com.autogain.repository.OperationRepository;
import br.com.autogain.repository.SignalRepository;
import br.com.autogain.service.IQOptionService;
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
    @Autowired
    private ConfigOperationRepository configOperationRepository;
    @Autowired
    private IQOptionService iqOptionService;


    @GetMapping
    public ResponseEntity<List<Signal>> listSignals(){
        List<Signal> signalList = signalRepository.findAll();
        return ResponseEntity.ok(signalList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Signal>> listIdSignals(@PathVariable String id){
        Optional<Signal> listSignals = signalRepository.findById(id);
        return ResponseEntity.ok(listSignals);
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

        if(osignal.isPresent() || !operations.isEmpty()) {
            osignal.get().setOperations(operations);
            signalRepository.save(osignal.get());
            return ResponseEntity.ok(osignal.get());
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Signal> UpdateSignals(@RequestBody Signal signal, @PathVariable String id){
        signal.setId(id);
        return ResponseEntity.ok(signalRepository.save(signal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSignals(@PathVariable String id){
        signalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{id}/configs")
    public ResponseEntity<Signal> signalsConfig(@PathVariable String id, @RequestBody ConfigOperation configOperation){

        Optional<Signal> csignal = signalRepository.findById(id);
        if(csignal.isPresent()) {
            csignal.get().setConfigOperation(configOperation);
            signalRepository.save(csignal.get());
            return ResponseEntity.ok(csignal.get());
        }
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/starter")
    public ResponseEntity<Signal> starterSignal(@PathVariable String id){
        Optional<Signal> osignal = signalRepository.findById(id);
        if (osignal.isPresent()){
            iqOptionService.openOperation(iqOption, osignal.get());
        }
        return  ResponseEntity.noContent().build();
    }
}
