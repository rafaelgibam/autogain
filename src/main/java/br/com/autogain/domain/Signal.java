package br.com.autogain.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigInteger;
import java.util.List;

@Setter
@Getter
@Entity(name = "signal")
public class Signal extends BaseEntity {
    private String name;
    private BigInteger quantityWin;
    private BigInteger quantityLoose;
    private BigInteger hitPercentage;


    @JsonIgnore
    @OneToMany
    private List<Operation> operations;

    @JsonIgnore
    @OneToOne
    private ConfigOperation configOperation;
}
