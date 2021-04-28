package br.com.autogain.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Data
@Document(collection = "messages")
public class Message extends BaseEntity {

	@JsonProperty("name")
	private String name;

	@JsonProperty("session_id")
	private String sessionId;

	@JsonProperty("msg")
	private String msg;
}
