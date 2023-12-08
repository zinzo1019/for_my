package com.example.HelloSpringboot.config.firebase.service;

import com.example.HelloSpringboot.config.firebase.object.NotificationRequest;
import com.example.HelloSpringboot.entity.User;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class NotificationService {

    private final Map<String, String> tokenMap = new HashMap<>();

    private final FCMService fcmService;

    public NotificationService(final FCMService fcmService) {
        this.fcmService = fcmService;
    }

    public void register(final String email, final String token) {
        tokenMap.put(email, token);
    }

    public void deleteToken(final String email) {
        tokenMap.remove(email);
    }

    public String getToken(final String email) {
        return tokenMap.get(email);
    }

    @SneakyThrows
    public void sendNotification(final NotificationRequest request) {
        try {
            fcmService.sendMessage(request);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void createReceiveNotification(User sender, User receiver) {
//        if (receiver.isLogin()) {
            NotificationRequest notificationRequest = NotificationRequest.builder()
                    .token(getToken(receiver.getEmail()))
                    .title("test title")
                    .message("test message")
                    .build();
            sendNotification(notificationRequest);
//        }
    }
}
