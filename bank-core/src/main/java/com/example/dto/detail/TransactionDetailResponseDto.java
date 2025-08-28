package com.example.dto.detail;

import com.example.dto.lite.AccountLiteResponseDto;
import com.example.enums.TransactionStatus;
import com.example.enums.TransactionType;
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
public class TransactionDetailResponseDto {
    private UUID transactionId;
    private String reference;
    private BigDecimal amount;
    private TransactionType type;
    private TransactionStatus status;
    private LocalDateTime createdAt;

    private AccountLiteResponseDto fromAccount;
    private AccountLiteResponseDto toAccount;
}
