package com.example.HelloSpringboot.service;

import com.example.HelloSpringboot.dto.LetterDto;
import com.example.HelloSpringboot.entity.Letter;

import java.time.LocalDate;
import java.util.List;

public interface LetterService {
    /** 편지 쓰기 */
    public void save(LetterDto letterDto);

    /** 편지 보기 */
    public Letter findLetterById(Long id);

    /** 특정 기간 동안 내가 받은 편지 리스트 가져오기 */
    public List<Letter> findReceiveLetters(LocalDate startDate, LocalDate endDate);

    /** 특정 기간 동안 내가 보낸 편지 리스트 가져오기 */
    List<Letter> findSendLetters(LocalDate StartDate, LocalDate endDate);

    /** 내가 받은 모든 편지 리스트 가져오기 */
    List<Letter> findReceiveLetters(Long userId);

    /** 내가 보낸 모든 편지 리스트 가져오기 */
    List<Letter> findSendLetters(Long userId);

    /** 커플 사이 모든 편지 리스트 가져오기 */
    List<Letter> findLettersByCouple(Long userId);
}
