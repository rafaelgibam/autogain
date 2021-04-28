package br.com.autogain.dto;

import br.com.autogain.domain.ConfigOperation;
import br.com.autogain.domain.Operation;
import lombok.Data;
import java.math.BigInteger;
import java.util.List;

@Data
public class SignalDTO {
    private String name;
    private BigInteger quantityWin;
    private BigInteger quantityLoose;
    private BigInteger hitPercentage;
    private List<Operation> operations;
    private ConfigOperation configOperation;
}
