package com.example.HelloSpringboot.config.firebase.object;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationRequest {
    private String token;
    private String title;
    private String message;
}
