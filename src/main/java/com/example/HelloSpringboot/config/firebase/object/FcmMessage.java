package com.example.HelloSpringboot.config.firebase.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FcmMessage {
    private boolean validateOnly;
    private Message message;

    @Data
    @Builder
    @AllArgsConstructor
    public static class Message {
        private Notification notification;
        private String token;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Notification {
        private String title;
        private String body;
        private String image;
    }
}