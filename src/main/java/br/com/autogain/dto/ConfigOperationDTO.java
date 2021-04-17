package br.com.autogain.dto;

import br.com.autogain.domain.Signal;
import br.com.autogain.enums.ManagementOperation;
import lombok.Data;

import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Data
public class ConfigOperationDTO {
    private BigDecimal price;
    private ManagementOperation managementOperation;
    private BigDecimal stop;
    private BigDecimal take;
    @OneToOne
    private Signal signal;
}
