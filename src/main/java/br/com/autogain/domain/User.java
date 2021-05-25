package br.com.autogain.domain;

import com.github.fabiomaffioletti.firebase.document.FirebaseDocument;
import com.github.fabiomaffioletti.firebase.document.FirebaseId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@FirebaseDocument("/users")
public class User {
    @FirebaseId
    private String id;
    private String email;
    private String password;
}
