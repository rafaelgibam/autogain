package br.com.autogain.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity(name = "balance")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "id", "user_id", "type", "amount", "enrolled_amount", "enrolled_sum_amount", "hold_amount",
		"orders_amount", "auth_amount", "equivalent", "currency", "tournament_id", "tournament_name", "is_fiat",
		"is_marginal", "has_deposits" })
public class Balance extends BaseEntity {

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
