package br.com.autogain.consumer.iqoption.ws.response;

import br.com.autogain.model.Balance;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
public class OperationResponse {
    @JsonProperty("id")
    private BigInteger id; // 8181085674
    @JsonProperty("refund")
    private BigInteger refund;
    @JsonProperty("win")
    private String win; // equal
    @JsonProperty("index")
    private BigInteger index; // 4646259912
    @JsonProperty("value")
    private BigDecimal value; // 1190650
    @JsonProperty("amount")
    private BigDecimal amount; // 10000000,
    @JsonProperty("params")
    private BigDecimal params;
    @JsonProperty("created")
    private Date created; // "2021-04-09T05:10:47+03:00",
    @JsonProperty("expired")
    private BigInteger expired;
    @JsonProperty("exp_time")
    private BigInteger expTime;
    @JsonProperty("type_name")
    private String typeName;
    @JsonProperty("type")
    private String type;
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
    @JsonProperty("now")
    private BigDecimal now;
    @JsonProperty("is_demo")
    private boolean isDemo; // true
    @JsonProperty("user_id")
    private BigInteger userId; // 96815802
    @JsonProperty("refund_value")
    private BigInteger refundValue;
    @JsonProperty("currency")
    private String currency; // USD
    @JsonProperty("currency_char")
    private String currencyChar;
    @JsonProperty("re_track")
    private String reTrack;
    @JsonProperty("active_id")
    private int activeId; // 1,
    @JsonProperty("active")
    private String active;
    @JsonProperty("direction")
    private String direction; // call
    @JsonProperty("exp_value")
    private BigInteger expValue; // 1190650,
    @JsonProperty("dir")
    private String dir;
    @JsonProperty("platform_id")
    private BigInteger platformId; // 82
    @JsonProperty("actual_expire")
    private Date actualExpire; // 2021-04-09T05:12:00+03:00
    @JsonProperty("profit_income")
    private BigDecimal profitIncome; // 160
    @JsonProperty("profit_return")
    private BigDecimal profitReturn; // 0
    @JsonProperty("option_type_id")
    private int optionTypeId; // 3
    @JsonProperty("enrolled_amount")
    private BigDecimal enrolledAmount; // 10000000
    @JsonProperty("expected_expire")
    private Date expectedExpire; // 2021-04-09T05:12:00+03:00
    @JsonProperty("user_balance_id")
    private BigInteger userBalanceId; // 478765880
    @JsonProperty("new_wager_amount")
    private BigDecimal newWagerAmount; // 0
    @JsonProperty("user_balance_type")
    private int userBalanceType; // 4
    @JsonProperty("new_balance_amount")
    private BigDecimal newBalanceAmount; // 10117990000,
    @JsonProperty("rollover_option_id")
    private BigInteger rolloverOptionId; // null,
    @JsonProperty("win_enrolled_amount")
    private BigDecimal winEnrolledAmount; // 10000000,
    @JsonProperty("rollover_commission_amount")
    private BigDecimal rolloverCommissionAmount; // null,
    @JsonProperty("rollover_commission_operation_id")
    private BigInteger rolloverCommissionOperationId; // null,
    @JsonProperty("rollover_commission_enrolled_amount")
    private BigDecimal rolloverCommissionEnrolledAmount; // null
    @JsonProperty("game_state")
    private BigInteger gameState;
    @JsonProperty("site_id")
    private BigInteger siteId;
    @JsonProperty("client_platform_id")
    private BigInteger clientPlatformId;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("exp")
    private BigInteger exp;
    @JsonProperty("message")
    private Object message;
    @JsonProperty("created_millisecond")
    private DateTime createdMillisecond;
    @JsonProperty("time_rate")
    private DateTime timeRate;
    @JsonProperty("act")
    private BigInteger act;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("open_time")
    private BigInteger openTime;
    @JsonProperty("robot_id")
    private BigInteger robotId;
    @JsonProperty("request_id")
    private BigInteger requestId;
    @JsonProperty("aff_id")
    private BigInteger affId;
    @JsonProperty("result")
    private String result;
    @JsonProperty("balance")
    private BigDecimal balance;
    @JsonProperty("option_id")
    private BigInteger optionId;
    @JsonProperty("aff_track")
    private String affTrack;
    @JsonProperty("balance_id")
    private BigInteger balanceId;
    @JsonProperty("bonus_rate")
    private BigInteger bonusRate;
    @JsonProperty("country_id")
    private BigInteger countryId;
    @JsonProperty("inout_diff")
    private BigInteger inoutDiff;
    @JsonProperty("option_type")
    private String optionType;
    @JsonProperty("is_can_trade")
    private Boolean isCanTrade;
    @JsonProperty("currency_mask")
    private String currencyMask;
    @JsonProperty("tournament_id")
    private String tournamentId;
    @JsonProperty("user_group_id")
    private BigInteger userGroupId;
    @JsonProperty("expiration_time")
    private DateTime expirationTime;
    @JsonProperty("balance_type_id")
    private BigInteger balanceTypeId;
    @JsonProperty("profit_percent")
    private BigInteger profitPercent;
    @JsonProperty("open_time_millisecond")
    private DateTime openTimeMillisecond;
    @JsonProperty("is_rolled_over")
    private Boolean isRolledOver;
    @JsonProperty("expiration_value")
    private BigDecimal expirationValue;
    @JsonProperty("current_balance")
    private Balance currentBalance;
    @JsonProperty("rollover_initial_commission_amount")
    private BigDecimal rolloverInitialCommissionAmount;
    @JsonProperty("rate_finished")
    private Boolean rateFinished;
}
