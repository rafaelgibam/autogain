package br.com.autogain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

import java.time.LocalDate;
import java.util.Date;


@Data
@Builder
@ApiModel("Operation")
public class OperationDTO {
    @JsonProperty("expiration")
    @ApiModelProperty(name = "expiration", position = 1, example = "Tempo de expiração do candle em minutos 1, 5, 15")
    private int expiration;

    @JsonProperty("direction")
    @ApiModelProperty(name = "direction", position = 2, example = "Direção da entrada CALL = Compora, PUT = Venda")
    private String direction;

    @JsonProperty("price")
    @ApiModelProperty(name = "price", position = 3, example = "Preço da entrada")
    private Double price;

    @JsonProperty("active")
    @ApiModelProperty(name = "active", position = 4, example = "Ativo ex: EURUSD, GBPJPY_OTC")
    private String active;

    @JsonProperty("entry_time")
    @ApiModelProperty(name = "entry_time", position = 5, example = "Tempo de entrada ex: 2022-01-01 00-00-00")
    private DateTime entryTime;
}
