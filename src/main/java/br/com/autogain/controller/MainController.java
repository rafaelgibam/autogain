package br.com.autogain.controller;


import br.com.autogain.consumer.iqoption.IQOption;
import br.com.autogain.consumer.iqoption.enums.Actives;
import br.com.autogain.consumer.iqoption.enums.BalanceType;
import br.com.autogain.consumer.iqoption.enums.BinaryBuyDirection;
import br.com.autogain.consumer.iqoption.event.Events;
import br.com.autogain.consumer.iqoption.rest.request.LoginRequest;
import br.com.autogain.consumer.iqoption.utils.IQUtils;
import br.com.autogain.consumer.iqoption.ws.request.BinaryBuyRequest;
import br.com.autogain.model.Operation;
import br.com.autogain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/autogains")
public class MainController {

    private IQOption iqOption;

    @PostMapping("/connects")
    public ResponseEntity<String> connectIqOption(
            @RequestBody User user
            ) {
        iqOption = new IQOption(user.getEmail(), user.getPassword());
        iqOption.connect();
        iqOption.getAllBinaryData();
        if(iqOption.isAuthenticated()) {
            log.info("connected... already!");
        }
        return ResponseEntity.ok("Connected... already!");
    }

    @PostMapping("/operations")
    public ResponseEntity<String> openOperation(
            @RequestBody Operation operation
            ) {

        iqOption.buyBinary(operation.getPrice(),
                           BinaryBuyDirection.valueOf(operation.getDirection()),
                           Actives.valueOf(operation.getActive()),
                           operation.getExpiration());

        return ResponseEntity.ok("Order open, Awaiting confirmation!");
    }

}
