package br.com.autogain.consumer.iqoption.ws.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class OperationOpened {
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("id")
    private BigInteger id;
    @JsonProperty("refund")
    private BigInteger refund;
    @JsonProperty("currency")
    private String     currency;
    @JsonProperty("currency_char")
    private String     currencyChar;
    @JsonProperty("active_id")
    private BigInteger activeId;
    @JsonProperty("active")
    private BigInteger active;
    @JsonProperty("value")
    private BigDecimal value;
    @JsonProperty("exp_value")
    private BigDecimal expValue;
    @JsonProperty("dir")
    private String     dir;
    @JsonProperty("created")
    private DateTime   created;
    @JsonProperty("expired")
    private DateTime   expired;
    @JsonProperty("exp_time")
    private DateTime   expTime;
    @JsonProperty("type_name")
    private String     typeName;
    @JsonProperty("type")
    private String     type;
    @JsonProperty("profit")
    private BigDecimal profit;
    @JsonProperty("profit_amount")
    private BigDecimal profitAmount;
    @JsonProperty("win_amount")
    private BigDecimal winAmount;
    @JsonProperty("loose_amount")
    private BigDecimal looseAmount;
    @JsonProperty("sum")
    private BigInteger sum;
    @JsonProperty("win")
    private String     win;
    @JsonProperty("now")
    private DateTime   now;
    @JsonProperty("user_id")
    private BigInteger userId;
    @JsonProperty("game_state")
    private BigInteger gameState;
    @JsonProperty("profit_income")
    private BigDecimal profitIncome;
    @JsonProperty("profit_return")
    private BigDecimal profitReturn;
    @JsonProperty("option_type_id")
    private BigInteger optionTypeId;
    @JsonProperty("site_id")
    private BigInteger siteId;
    @JsonProperty("is_demo")
    private Boolean    isDemo;
    @JsonProperty("user_balance_id")
    private BigInteger userBalanceId;
    @JsonProperty("client_platform_id")
    private BigInteger clientPlatformId;
    @JsonProperty("re_track")
    private String     reTrack;
}
