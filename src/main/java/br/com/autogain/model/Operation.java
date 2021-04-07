package br.com.autogain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Operation {
    @JsonProperty("expiration")
    private int expiration;
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("active")
    private String active;
}
