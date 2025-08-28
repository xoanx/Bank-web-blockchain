package com.example.dto.lite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data@Builder
public class AccountLiteResponseDto {
    private UUID idAccount;
    private String accountNumber;
    private String username;
    private BigDecimal balance;
    private String currency;
}
