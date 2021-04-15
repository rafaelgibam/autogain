package br.com.autogain.domain;

import br.com.autogain.enums.ManagementOperation;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity(name = "cofing_operation")
public class ConfigOperation extends BaseEntity{
    private BigDecimal price;
    private ManagementOperation managementOperation;
    private BigDecimal stop;
    private BigDecimal take;
    @OneToMany
    private List<Signal> signal;
}
