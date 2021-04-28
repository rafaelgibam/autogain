package br.com.autogain.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "messages")
public class Message {

	@Id
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("session_id")
	private String sessionId;

	@JsonProperty("msg")
	private String msg;
}
