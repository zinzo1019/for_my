package com.example.HelloSpringboot.dto;

import com.example.HelloSpringboot.entity.Couple;
import com.example.HelloSpringboot.entity.User;
import lombok.Data;

@Data
public class CoupleDto {
    private Long id;
    private User user1;
    private User user2;

    public Couple dtoToEntity() {
        return Couple.builder()
                .id(this.id)
                .user1(this.user1)
                .user2(this.user2)
                .build();
    }
}
