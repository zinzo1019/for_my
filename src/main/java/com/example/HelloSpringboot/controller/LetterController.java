package com.example.HelloSpringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/letter")
public class LetterController {

    @GetMapping("/{id}")
    public String letterView(@PathVariable("id") Long id) {
        return "user/letter_view";
    }
    @GetMapping("/send_letter")
    public String sendLetter() {
        return "user/send_letter";
    }

}
