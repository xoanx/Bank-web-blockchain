package com.example.dto.detail;

import com.example.dto.lite.AccountLiteResponseDto;
import com.example.dto.lite.TransactionLiteResponseDto;
import com.example.enums.EntryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data@Builder
public class LedgerEntryDetailResponseDto {
    private UUID id;
    private BigDecimal amount;
    private BigDecimal balanceAfter;
    private EntryType entryType;
    private LocalDateTime createdAt;

    private TransactionLiteResponseDto transaction;
    private AccountLiteResponseDto account;
}
