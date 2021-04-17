package br.com.autogain.dto;

import br.com.autogain.domain.ConfigOperation;
import br.com.autogain.domain.Operation;
import lombok.Data;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigInteger;
import java.util.List;

@Data
public class SignalDTO {
    private String name;
    private BigInteger quantityWin;
    private BigInteger quantityLoose;
    private BigInteger hitPercentage;
    @OneToMany
    private List<Operation> operations;
    @OneToOne
    private ConfigOperation configOperation;
}
