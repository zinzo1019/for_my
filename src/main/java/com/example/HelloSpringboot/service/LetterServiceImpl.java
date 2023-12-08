package com.example.HelloSpringboot.service;

import com.example.HelloSpringboot.dto.LetterDto;
import com.example.HelloSpringboot.entity.Letter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LetterServiceImpl implements LetterService {

    /** 편지 쓰기 */
    @Override
    public void save(LetterDto letterDto) {
    }

    /** 편지 보기 */
    @Override
    public Letter findLetterById(Long id) {
        return null;
    }

    /** 특정 기간 동안 내가 받은 편지 리스트 가져오기 */
    @Override
    public List<Letter> findReceiveLetters(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    /** 특정 기간 동안 내가 보낸 편지 리스트 가져오기 */
    @Override
    public List<Letter> findSendLetters(LocalDate StartDate, LocalDate endDate) {
        return null;
    }

    /** 내가 받은 모든 편지 리스트 가져오기 */
    @Override
    public List<Letter> findReceiveLetters(Long userId) {
        return null;
    }

    /** 내가 보낸 모든 편지 리스트 가져오기 */
    @Override
    public List<Letter> findSendLetters(Long userId) {
        return null;
    }

    /** 커플 사이 모든 편지 리스트 가져오기 */
    @Override
    public List<Letter> findLettersByCouple(Long userId) {
        return null;
    }
}
