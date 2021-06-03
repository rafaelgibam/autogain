package br.com.autogain.domain;

import br.com.autogain.enums.ManagementOperation;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ConfigOperation {
    @DocumentId
    private String id;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("management_operation")
    private ManagementOperation managementOperation;
    @JsonProperty("stop")
    private Double stop;
    @JsonProperty("take")
    private Double take;
}
