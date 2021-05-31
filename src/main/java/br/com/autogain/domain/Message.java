package br.com.autogain.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Message {

	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("session_id")
	private String sessionId;

	@JsonProperty("msg")
	private String msg;
}
