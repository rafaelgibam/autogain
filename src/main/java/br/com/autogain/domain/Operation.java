package br.com.autogain.domain;

import br.com.autogain.config.CustomDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Data
@Builder
@Document(collation = "operations")
public class Operation extends BaseEntity{

    @JsonProperty("expiration")
    private int expiration;

    @JsonProperty("direction")
    private String direction;

    @JsonProperty("active")
    private String active;

    @JsonProperty("entry_time")
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime entryTime;

    @JsonProperty("status")
    private boolean status;


}
