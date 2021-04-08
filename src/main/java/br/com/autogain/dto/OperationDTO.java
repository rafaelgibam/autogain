package br.com.autogain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class OperationDTO {
    @JsonProperty("expiration")
    private int expiration;
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("active")
    private String active;
    @JsonProperty("entry_time")
    private LocalDate entryTime;
}
