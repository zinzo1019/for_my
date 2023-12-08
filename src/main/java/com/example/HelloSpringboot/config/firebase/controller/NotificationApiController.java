package com.example.HelloSpringboot.config.firebase.controller;

import com.example.HelloSpringboot.config.firebase.service.NotificationService;
import com.example.HelloSpringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationApiController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody String token) {
        notificationService.register(UserService.getUserDetails().getEmail(), token);
        return ResponseEntity.ok().build();
    }

}
