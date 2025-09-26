package com.example.entity;

import com.example.enums.TransactionStatus;
import com.example.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = { @Index (name = "idx_tx_reference", columnList = "reference", unique = true)})
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, updatable = false)
    private String reference;
    @Column(name = "Amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "Type", nullable = false)
    private TransactionType type;
    @Column(name = "Status", nullable = false)
    private TransactionStatus status;
    @Column(name = "Created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "From_account_id", nullable = false)
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "To_account_id", nullable = false)
    private Account toAccount;
    @JoinColumn(name = "block_chain_Tx_hash", nullable = false)
    private String blockchainTxHash;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
