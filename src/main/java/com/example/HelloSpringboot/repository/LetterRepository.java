package com.example.HelloSpringboot.repository;

import com.example.HelloSpringboot.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LetterRepository extends JpaRepository<Letter, Long> {
    /** 편지 아이디로 편지 가져오기 */
    Letter findLetterById(Long id);
    /** 특정 기간 동안 내가 받은 편지 리스트 가져오기 */
    List<Letter> findAllByReceiveUser_IdAndReservationBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    /** 특정 기간 동안 내가 보낸 편지 리스트 가져오기 */
    List<Letter> findAllBySendUser_IdAndReservationBetween(Long userId, LocalDateTime StartDate, LocalDateTime endDate);
    /** 내가 받은 모든 편지 리스트 가져오기 */
    List<Letter> findAllByReceiveUser_Id(Long userId);
    /** 내가 보낸 모든 편지 리스트 가져오기 */
    List<Letter> findAllBySendUser_Id(Long userId);
    /** 커플 사이 모든 편지 리스트 가져오기 */
    List<Letter> findAllBySendUser_IdOrReceiveUser_IdOrderByReservation(Long userId1, Long userId2);
}
