package br.com.autogain.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection ="users")
public class User {
    @Id
    private Long id;
    private String email;
    private String password;
}
