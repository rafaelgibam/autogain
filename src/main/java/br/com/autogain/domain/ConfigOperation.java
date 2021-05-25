package br.com.autogain.domain;

import br.com.autogain.enums.ManagementOperation;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.fabiomaffioletti.firebase.document.FirebaseDocument;
import com.github.fabiomaffioletti.firebase.document.FirebaseId;
import lombok.Data;
import java.math.BigDecimal;

@Data
@FirebaseDocument("/config_operations")
public class ConfigOperation {
    @FirebaseId
    private String id;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("management_operation")
    private ManagementOperation managementOperation;
    @JsonProperty("stop")
    private BigDecimal stop;
    @JsonProperty("take")
    private BigDecimal take;
}
