package com.example.entity;

import com.example.enums.TransactionStatus;
import com.example.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn (name = "from_account_id", nullable = false)
    private Account fromAccount;
    @ManyToOne
    @JoinColumn(name = "to_account_id", nullable = false)
    private Account toAccount;
    private Double amount;
    @Enumerated (EnumType.STRING)
    private TransactionType type;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
