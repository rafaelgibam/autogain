package br.com.autogain.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Data
@Builder
@Entity(name = "operation")
public class Operation extends BaseEntity{
    @JsonProperty("expiration")
    private int expiration;
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("active")
    private String active;
    @Lob
    @Column(length = 10000, columnDefinition="TEXT")
    @JsonProperty("entry_time")
    private DateTime entryTime;
    @JsonIgnore
    @JsonProperty("status")
    private boolean status;
    @JsonIgnore
    @OneToOne
    @JsonProperty("signal")
    private Signal signal;
}
