package com.example.bankproject.domain.account;


import com.example.bankproject.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name="account_tb")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private Long number;  // 계좌번호

    @Column(nullable = false, length = 4)
    private Long password;  // 계좌 비밀번호

    @Column(nullable = false)
    private Long balance;  // 잔액 (기본값 1000원)
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;


    // --------------> fk는 many쪽 !
    // 저장될 때 user_id 로 저장됨
    // account.getUser().아무필드 호출() ==> lazy 발동 !
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    @Builder
    public Account(Long id, Long number, Long password, Long balance,
                   LocalDateTime createdAt, LocalDateTime updatedAt, User user) {
        this.id = id;
        this.number = number;
        this.password = password;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }
}
