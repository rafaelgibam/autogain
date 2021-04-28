package br.com.autogain.domain;

import br.com.autogain.enums.ManagementOperation;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.List;

@Data
@Document(collection = "config_operations")
public class ConfigOperation extends BaseEntity{
    private BigDecimal price;
    private ManagementOperation managementOperation;
    private BigDecimal stop;
    private BigDecimal take;
    private List<Signal> signal;
}
