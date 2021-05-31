package br.com.autogain.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;
import org.joda.time.DateTime;
import java.util.Date;

@Data
public class EventMessage {
        @DocumentId
        private String id;
        @JsonProperty("refund")
        private Integer refund;
        @JsonProperty("win")
        private String win; // equal
        @JsonProperty("index")
        private Integer index; // 4646259912
        @JsonProperty("value")
        private Double value; // 1190650
        @JsonProperty("amount")
        private Double amount; // 10000000,
        @JsonProperty("params")
        private Double params;
        @JsonProperty("created")
        private Date created; // "2021-04-09T05:10:47+03:00",
        @JsonProperty("expired")
        private Integer expired;
        @JsonProperty("exp_time")
        private Integer expTime;
        @JsonProperty("type_name")
        private String typeName;
        @JsonProperty("type")
        private String type;
        @JsonProperty("profit")
        private Double profit;
        @JsonProperty("profit_amount")
        private Double profitAmount;
        @JsonProperty("win_amount")
        private Double winAmount;
        @JsonProperty("loose_amount")
        private Double looseAmount;
        @JsonProperty("sum")
        private Integer sum;
        @JsonProperty("now")
        private Double now;
        @JsonProperty("is_demo")
        private boolean isDemo; // true
        @JsonProperty("user_id")
        private Integer userId; // 96815802
        @JsonProperty("refund_value")
        private Integer refundValue;
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
        private Integer expValue; // 1190650,
        @JsonProperty("dir")
        private String dir;
        @JsonProperty("platform_id")
        private Integer platformId; // 82
        @JsonProperty("actual_expire")
        private Date actualExpire; // 2021-04-09T05:12:00+03:00
        @JsonProperty("profit_income")
        private Double profitIncome; // 160
        @JsonProperty("profit_return")
        private Double profitReturn; // 0
        @JsonProperty("option_type_id")
        private int optionTypeId; // 3
        @JsonProperty("enrolled_amount")
        private Double enrolledAmount; // 10000000
        @JsonProperty("expected_expire")
        private Date expectedExpire; // 2021-04-09T05:12:00+03:00
        @JsonProperty("user_balance_id")
        private Integer userBalanceId; // 478765880
        @JsonProperty("new_wager_amount")
        private Double newWagerAmount; // 0
        @JsonProperty("user_balance_type")
        private int userBalanceType; // 4
        @JsonProperty("new_balance_amount")
        private Double newBalanceAmount; // 10117990000,
        @JsonProperty("rollover_option_id")
        private Integer rolloverOptionId; // null,
        @JsonProperty("win_enrolled_amount")
        private Double winEnrolledAmount; // 10000000,
        @JsonProperty("rollover_commission_amount")
        private Double rolloverCommissionAmount; // null,
        @JsonProperty("rollover_commission_operation_id")
        private Integer rolloverCommissionOperationId; // null,
        @JsonProperty("rollover_commission_enrolled_amount")
        private Double rolloverCommissionEnrolledAmount; // null
        @JsonProperty("game_state")
        private Integer gameState;
        @JsonProperty("site_id")
        private Integer siteId;
        @JsonProperty("client_platform_id")
        private Integer clientPlatformId;
        @JsonProperty("price")
        private Double price;
        @JsonProperty("exp")
        private Integer exp;
        @JsonProperty("message")
        private Message message;
        @JsonProperty("created_millisecond")
        private DateTime createdMillisecond;
        @JsonProperty("time_rate")
        private DateTime timeRate;
        @JsonProperty("act")
        private Integer act;
        @JsonProperty("reason")
        private String reason;
        @JsonProperty("open_time")
        private Integer openTime;
        @JsonProperty("robot_id")
        private Integer robotId;
        @JsonProperty("request_id")
        private Integer requestId;
        @JsonProperty("aff_id")
        private Integer affId;
        @JsonProperty("result")
        private String result;
        @JsonProperty("balance")
        private Double balance;
        @JsonProperty("option_id")
        private Integer optionId;
        @JsonProperty("aff_track")
        private String affTrack;
        @JsonProperty("balance_id")
        private Integer balanceId;
        @JsonProperty("bonus_rate")
        private Integer bonusRate;
        @JsonProperty("country_id")
        private Integer countryId;
        @JsonProperty("inout_diff")
        private Integer inoutDiff;
        @JsonProperty("option_type")
        private String optionType;
        @JsonProperty("is_can_trade")
        private Boolean isCanTrade;
        @JsonProperty("currency_mask")
        private String currencyMask;
        @JsonProperty("tournament_id")
        private String tournamentId;
        @JsonProperty("user_group_id")
        private Integer userGroupId;
        @JsonProperty("expiration_time")
        private String expirationTime;
        @JsonProperty("balance_type_id")
        private Integer balanceTypeId;
        @JsonProperty("profit_percent")
        private Integer profitPercent;
        @JsonProperty("open_time_millisecond")
        private Date openTimeMillisecond;
        @JsonProperty("is_rolled_over")
        private Boolean isRolledOver;
        @JsonProperty("expiration_value")
        private Double expirationValue;
        @JsonProperty("current_balance")
        private Balance currentBalance;
        @JsonProperty("rollover_initial_commission_amount")
        private Double rolloverInitialCommissionAmount;
        @JsonProperty("rate_finished")
        private Boolean rateFinished;
}
