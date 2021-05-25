package br.com.autogain;


import com.github.fabiomaffioletti.firebase.EnableFirebaseRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFirebaseRepositories
public class AutoGainApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutoGainApplication.class, args);
    }
}
