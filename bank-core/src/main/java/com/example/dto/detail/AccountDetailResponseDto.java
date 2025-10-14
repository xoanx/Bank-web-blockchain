package com.example.dto.detail;

import com.example.dto.lite.PersonLiteResponseDto;
import com.example.enums.AccountStatus;
import com.example.enums.UserRole;
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
public class AccountDetailResponseDto {
    private UUID idAccount;
    private String accountNumber;
    private String username;
    private AccountStatus accountStatus;
    private UserRole role;
    private BigDecimal balance;
    private String currency;
    private LocalDateTime logInAt;
    private LocalDateTime logOutAt;
    private String blockchainAddress;

    private PersonLiteResponseDto person;
}
