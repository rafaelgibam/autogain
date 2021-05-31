package br.com.autogain.domain;

import br.com.autogain.config.CustomDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.*;
import org.joda.time.DateTime;

@Data
@Builder
public class Operation {

    @DocumentId
    private String id;

    @JsonIgnore
    @JsonProperty("signal_id")
    private String signalId;

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
    private DateTime entryTime;

    @JsonProperty("status")
    private Boolean status;

}
