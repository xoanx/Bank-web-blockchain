package com.example.dto.lite;

import com.example.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data@Builder
public class TransactionLiteResponseDto {
    private UUID transactionId;
    private String reference;
    private BigDecimal amount;
    private TransactionStatus status;
}
