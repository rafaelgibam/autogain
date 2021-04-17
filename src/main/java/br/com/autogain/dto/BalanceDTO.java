package br.com.autogain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BalanceDTO {
    @JsonProperty("user_id")
    private long userId;
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("enrolled_amount")
    private Integer enrolledAmount;
    @JsonProperty("enrolled_sum_amount")
    private Integer enrolledSumAmount;
    @JsonProperty("hold_amount")
    private Integer holdAmount;
    @JsonProperty("orders_amount")
    private Integer ordersAmount;
    @JsonProperty("auth_amount")
    private Integer authAmount;
    @JsonProperty("equivalent")
    private Integer equivalent;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("tournament_id")
    private String tournamentId;
    @JsonProperty("tournament_name")
    private String tournamentName;
    @JsonProperty("is_fiat")
    private Boolean isFiat;
    @JsonProperty("is_marginal")
    private Boolean isMarginal;
    @JsonProperty("has_deposits")
    private Boolean hasDeposits;
}
