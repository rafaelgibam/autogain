package br.com.autogain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

import java.time.LocalDate;
import java.util.Date;


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
    private DateTime entryTime;
}
