package br.com.autogain.domain;

import br.com.autogain.config.CustomDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Operation {

    @JsonProperty("expiration")
    private int expiration;

    @JsonProperty("direction")
    private String direction;

    @JsonProperty("active")
    private String active;

    @JsonIgnore
    private Double price;

    @JsonProperty("entry_time")
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private Date entryTime;

    @JsonProperty("status")
    private Boolean status;

}
