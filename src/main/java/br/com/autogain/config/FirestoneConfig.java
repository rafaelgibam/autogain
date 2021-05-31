package br.com.autogain.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirestoneConfig {

    @Bean
    public Firestore getFireStore(@Value("${firebase.service-account-filename}") String credentialPath) throws IOException {
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream(credentialPath);
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

        FirestoreOptions options = FirestoreOptions.newBuilder()
                .setCredentials(credentials).build();

        return options.getService();

    }
}
