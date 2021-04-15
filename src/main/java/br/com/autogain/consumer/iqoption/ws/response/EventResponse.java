package br.com.autogain.consumer.iqoption.ws.response;

import br.com.autogain.domain.Message;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EventResponse {
    @JsonProperty("name")
    private String name; // "option-archived",
    @JsonProperty("microserviceName")
    private String microserviceName; // "binary-options",
    @JsonProperty("msg")
    private Message msg;
 }
