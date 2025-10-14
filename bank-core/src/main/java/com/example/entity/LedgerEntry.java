package com.example.entity;

import com.example.enums.EntryType;
import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LedgerEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "Entry_type", nullable = false)
    private EntryType entryType;
    @Column(name = "Amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "Balance_after", nullable = false)
    private BigDecimal balanceAfter;
    @Column(name = "Created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "Account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "Transaction_id", nullable = false)
    private Transaction transaction;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
