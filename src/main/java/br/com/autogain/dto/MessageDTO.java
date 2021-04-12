package br.com.autogain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class MessageDTO {
        @JsonProperty("id")
        private int id; // 8181085674
        @JsonProperty("win")
        private String win; // equal
        @JsonProperty("index")
        private int index; // 4646259912
        @JsonProperty("value")
        private Double value; // 1190650
        @JsonProperty("amount")
        private Double amount; // 10000000,
        @JsonProperty("params")
        private Double params;
        @JsonProperty("created")
        private Date created; // "2021-04-09T05:10:47+03:00",
        @JsonProperty("is_demo")
        private boolean isDemo; // true
        @JsonProperty("user_id")
        private int userId; // 96815802
        @JsonProperty("currency")
        private String currency; // USD
        @JsonProperty("re_track")
        private String reTrack;
        @JsonProperty("active_id")
        private int activeId; // 1,
        @JsonProperty("direction")
        private String direction; // call
        @JsonProperty("exp_value")
        private int expValue; // 1190650,
        @JsonProperty("win_amount")
        private Double winAmount; // 10000000,
        @JsonProperty("platform_id")
        private int platformId; // 82
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
        private int userBalanceId; // 478765880
        @JsonProperty("new_wager_amount")
        private Double newWagerAmount; // 0
        @JsonProperty("user_balance_type")
        private int userBalanceType; // 4
        @JsonProperty("new_balance_amount")
        private Double newBalanceAmount; // 10117990000,
        @JsonProperty("rollover_option_id")
        private int rolloverOptionId; // null,
        @JsonProperty("win_enrolled_amount")
        private Double winEnrolledAmount; // 10000000,
        @JsonProperty("rollover_commission_amount")
        private Double rolloverCommissionAmount; // null,
        @JsonProperty("rollover_commission_operation_id")
        private int rolloverCommissionOperationId; // null,
        @JsonProperty("rollover_commission_enrolled_amount")
        private Double rolloverCommissionEnrolledAmount; // null
}
