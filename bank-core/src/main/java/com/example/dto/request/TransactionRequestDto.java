package com.example.dto.request;

import com.example.entity.Account;
import com.example.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TransactionRequestDto {
    private Account fromAccount;
    private Account toAccount;
    private BigDecimal amount;
    private TransactionType type;
}
