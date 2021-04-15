package br.com.autogain.domain;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity(name="user")
public class User extends BaseEntity{
    private String email;
    private String password;
}
