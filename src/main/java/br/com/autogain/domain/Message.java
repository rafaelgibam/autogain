package br.com.autogain.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.fabiomaffioletti.firebase.document.FirebaseDocument;
import com.github.fabiomaffioletti.firebase.document.FirebaseId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@FirebaseDocument("/messages")
public class Message {

	@FirebaseId
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("session_id")
	private String sessionId;

	@JsonProperty("msg")
	private String msg;
}
