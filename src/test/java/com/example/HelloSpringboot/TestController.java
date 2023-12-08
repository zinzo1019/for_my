package com.example.HelloSpringboot;

import com.example.HelloSpringboot.entity.Letter;
import com.example.HelloSpringboot.entity.User;
import com.example.HelloSpringboot.repository.LetterRepository;
import com.example.HelloSpringboot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class TestController {

    @Autowired
    private LetterRepository letterRepository;
    @Autowired
    private UserRepository userRepository;

    /** 편지 저장하기 */
    @Test
    public void saveLetters() {

        List<Letter> letters = new ArrayList<>();

        User user1 = userRepository.findByEmail("20190996@sungshin.ac.kr");
        User user2 = userRepository.findByEmail("user2@naver.com");

        IntStream.rangeClosed(1, 5)
                        .forEach(number -> {
                            Letter letter = Letter.builder()
                                    .content("편지 내용 " + number)
                                    .dear("연인에게")
                                    .sendUser(user1)
                                    .receiveUser(user2)
                                    .reservation(LocalDateTime.now())
                                    .read(false)
                                    .build();
                            letters.add(letter);
                        });

        letterRepository.saveAll(letters);
    }

    /** 커플 사이 편지 가져오기 */
    @Test
    public void findLettersByCouple() {

        User user1 = userRepository.findByEmail("20190996@sungshin.ac.kr");
        User user2 = userRepository.findByEmail("user2@naver.com");

        List<Letter> letters = letterRepository.findAllBySendUser_IdOrReceiveUser_IdOrderByReservation(user1.getId(), user2.getId());
        System.out.println(letters.toString());
    }
}
