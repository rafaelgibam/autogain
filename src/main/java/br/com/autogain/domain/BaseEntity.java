package br.com.autogain.domain;


import org.springframework.data.annotation.Id;
import javax.persistence.Inheritance;


@Inheritance
public abstract class BaseEntity {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
