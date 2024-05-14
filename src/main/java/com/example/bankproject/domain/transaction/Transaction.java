package com.example.bankproject.domain.transaction;


import com.example.bankproject.domain.account.Account;
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
@Table(name="transaction_tb")
@Entity
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account withdrawAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account depositAccount;

    private Long amount;  // 입금 양

    private Long withdrawAccountBalance;   // history

    private Long depositAccountBalance;    // history

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionEnum gubun;  // WITHDRAW, DEPOSIT, TRANSFER, ALL


    // 계좌가 사라져도 로그는 남아야.
    private String sender;
    private String receiver;
    private String tel;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;


    @Builder
    public Transaction(Long id, Account withdrawAccount, Account depositAccount, Long amount,
                       Long withdrawAccountBalance, Long depositAccountBalance,
                       TransactionEnum gubun,
                       String sender, String receiver, String tel, LocalDateTime createdAt,
                       LocalDateTime updatedAt) {
        this.id = id;
        this.withdrawAccount = withdrawAccount;
        this.depositAccount = depositAccount;
        this.amount = amount;
        this.withdrawAccountBalance = withdrawAccountBalance;
        this.depositAccountBalance = depositAccountBalance;
        this.gubun = gubun;
        this.sender = sender;
        this.receiver = receiver;
        this.tel = tel;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


}
