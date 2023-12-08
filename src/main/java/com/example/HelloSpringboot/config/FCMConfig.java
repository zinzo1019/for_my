package com.example.HelloSpringboot.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FCMConfig {

    @Value("${FCM}")
    private String FCM;
    @Value("${FCM_FULL_PATH}")
    private String FCM_FULL_PATH;

//    @Bean
//    public FirebaseApp firebaseApp() throws IOException {
//        FileInputStream serviceAccountFile = new FileInputStream("src/main/resources/" + FCM);
//        FirebaseOptions options = FirebaseOptions
//                .builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccountFile))
//                .build();
//        return FirebaseApp.initializeApp(options);
//    }

    @PostConstruct
    public void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(FCM).getInputStream())).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase application has been initialized");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Bean
//    public FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
//        return FirebaseMessaging.getInstance(firebaseApp);
//    }
}