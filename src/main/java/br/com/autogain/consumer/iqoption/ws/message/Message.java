package br.com.autogain.consumer.iqoption.ws.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Message {
	@JsonProperty("name")
	private String name;
	@JsonProperty("session_id")
	private String sessionId;
	@JsonProperty("msg")
	private String msg;
}
