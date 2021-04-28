package br.com.autogain.domain;

import br.com.autogain.enums.ManagementOperation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.util.List;

@Data
@Document(collection = "config_operations")
public class ConfigOperation {
    @Id
    private Long id;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("management_operation")
    private ManagementOperation managementOperation;
    @JsonProperty("stop")
    private BigDecimal stop;
    @JsonProperty("take")
    private BigDecimal take;
    @JsonProperty("signals")
    private List<Signal> signals;
}
