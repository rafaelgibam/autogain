package br.com.autogain.dto;

import br.com.autogain.domain.ConfigOperation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestAutoOperation {
    @JsonProperty("expiration")
    private Integer expiration;
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("active")
    private String active;
}
