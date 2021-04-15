package br.com.autogain.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "message")
public class Message extends BaseEntity {

	@JsonProperty("name")
	private String name;

	@JsonProperty("session_id")
	private String sessionId;

	@Lob
	@Column(length = 10000, columnDefinition="TEXT")
	@JsonProperty("msg")
	private String msg;
}
