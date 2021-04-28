package br.com.autogain.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigInteger;
import java.util.List;

@Data
@Document(collection = "signals")
public class Signal {
    @Id
    private Long id;

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
