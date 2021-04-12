package br.com.autogain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

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
