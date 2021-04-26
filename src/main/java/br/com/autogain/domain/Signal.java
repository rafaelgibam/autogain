package br.com.autogain.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigInteger;
import java.util.List;


@Data
@Document(collation = "signals")
public class Signal extends BaseEntity {

    private String name;
    private BigInteger quantityWin;
    @JsonProperty("quantity_loose")
    private BigInteger quantityLoose;
    @JsonProperty("hit_percentage")
    private BigInteger hitPercentage;
    private List<Operation> operations;
    @JsonProperty("config_operation")
    private ConfigOperation configOperation;
}
