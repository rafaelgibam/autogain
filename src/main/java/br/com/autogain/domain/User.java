package br.com.autogain.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;

@Data
@Document(collation ="users")
public class User extends BaseEntity{
    private String email;
    private String password;
}
