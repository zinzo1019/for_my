package com.example.HelloSpringboot.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA Entity 클래스들이 해당 추상 클래스를 상속할 경우 createDate, modifiedDate를 컬럼으로 인식
@EntityListeners(AuditingEntityListener.class) //BaseTimeEntity 클래스에 Auditing 기능을 포함시킴
public abstract class BaseTime {
    @CreatedDate
    private LocalDateTime createDate; //Entity가 생성되어 저장될 때 시간이 자동 저장

    @LastModifiedDate
    private LocalDateTime modifiedDate; //조회한 Entity의 값을 변경할 때 시간이 자동 저장
}