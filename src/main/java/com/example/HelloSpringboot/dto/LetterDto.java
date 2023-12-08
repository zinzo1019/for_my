package com.example.HelloSpringboot.dto;

import com.example.HelloSpringboot.entity.Letter;
import com.example.HelloSpringboot.entity.User;
import lombok.Data;

@Data
public class LetterDto {
    private Long id;
    private String content;
    private String dear;
    private User sendUser;
    private User receiveUser;

    public Letter dtoToEntity() {
        return Letter.builder()
                .id(this.id)
                .content(this.content)
                .dear(this.dear)
                .sendUser(this.sendUser)
                .receiveUser(this.receiveUser)
                .build();
    }
}
