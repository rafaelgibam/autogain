package br.com.autogain.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    private String id;
    private String email;
    private String password;
}
