package br.com.autogain.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Signal {
    @DocumentId
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("quantity_win")
    private Integer quantityWin;
    @JsonProperty("quantity_loose")
    private Integer quantityLoose;
    @JsonProperty("hit_percentage")
    private Integer hitPercentage;
    private List<Operation> operations;
    @JsonProperty("config_operation")
    private ConfigOperation configOperation;
}
