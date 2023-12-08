package com.example.HelloSpringboot.controller;

import com.example.HelloSpringboot.config.firebase.object.NotificationRequest;
import com.example.HelloSpringboot.config.firebase.service.FCMService;
import com.example.HelloSpringboot.dto.UserDto;
import com.example.HelloSpringboot.service.UserService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private FCMService firebaseMessagingService;

    @Value("${IMAGE_PATH}")
    private String IMAGE_PATH;

    /** 로그인 전 메인 페이지 */
    @GetMapping({"/public", "/public/main"})
    public String publicPage() {
        return "/public/main";
    }

    /** 로그인 페이지 */
    @GetMapping({"/", "/login"})
    public String loginPage(Model model) {
        model.addAttribute("IMAGE_PATH", IMAGE_PATH);
        return "/public/login";
    }

    /** 회원가입 페이지 */
    @GetMapping("signup")
    public String signupPage() {
        return "/public/signup";
    }

    /** 회원가입 ACTION */
    @PostMapping("/join")
    public ResponseEntity<String> joinPage(UserDto userDto) {
        try {
            userService.save(userDto); // 회원가입
            return ResponseEntity.ok(String.format("[%s]님, 환영합니다!", userDto.getUsername()));
        } catch (DuplicateKeyException e) { // 이미 가입된 회원
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) { // 그 외 에러
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user.");
        }
    }

    /** 로그인 성공 후 메인 페이지 */
    @GetMapping({"/user", "/user/main"})
    public String userMainPage() {
        System.out.println("로그인 한 사용자 => " + UserService.getUserDetails().getEmail());
        return "/user/main";
    }

    @SneakyThrows
    @PostMapping("/api/fcm")
    public void pushMessage(@RequestBody NotificationRequest notificationRequest) throws IOException, FirebaseMessagingException {
        try {
            String accessToken = firebaseMessagingService.getAccessToken();

            System.out.println("2:::" + accessToken);
            System.out.println("3:::" + notificationRequest.getTitle());
            System.out.println("4:::" + notificationRequest.getMessage());

            firebaseMessagingService.sendMessage(notificationRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
