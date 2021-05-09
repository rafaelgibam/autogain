package br.com.autogain.domain;

import br.com.autogain.config.CustomDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Data
@Builder
@Document(collection = "operations")
public class Operation {

    @Id
    private String id;

    @JsonIgnore
    private String signalId;

    @JsonProperty("expiration")
    private int expiration;

    @JsonProperty("direction")
    private String direction;

    @JsonProperty("active")
    private String active;

    @JsonIgnore
    private BigDecimal price;



    @JsonProperty("entry_time")
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime entryTime;

    @JsonProperty("status")
    private Boolean status;


}
