package com.example.HelloSpringboot.dto;

import com.example.HelloSpringboot.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String role;
    private String username;
    private String nickname;
    private String lastName;
    private String firstName;
    private LocalDate birth;
    private String tel;
    private LocalDate createDate;

    public User dtoToEntity() {
        return User.builder()
                .id(this.id)
                .email(this.email)
                .password(this.password)
                .role(this.role)
                .username(this.username)
                .lastName(this.lastName)
                .firstName(this.firstName)
                .birth(this.birth)
                .tel(this.tel)
                .nickname(this.nickname)
                .build();
    }
}
