package com.example.HelloSpringboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Letter extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String dear;
    private LocalDateTime reservation;
    private boolean read;

    @ManyToOne
    private User sendUser;
    @ManyToOne
    private User receiveUser;

    /** 편지 열람 */
    @Transactional
    public void updateRead(boolean read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", dear='" + dear + '\'' +
                ", reservation=" + reservation +
                ", read=" + read +
                ", sendUser=" + sendUser +
                ", receiveUser=" + receiveUser +
                '}';
    }
}
