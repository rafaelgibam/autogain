package br.com.autogain.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@Entity(name = "signal")
public class Signal extends BaseEntity {
    private String name;
    private BigInteger quantityWin;
    private BigInteger quantityLoose;
    private BigInteger hitPercentage;
    @OneToMany
    private List<Operation> operations;
    @OneToOne
    private ConfigOperation configOperation;
}
