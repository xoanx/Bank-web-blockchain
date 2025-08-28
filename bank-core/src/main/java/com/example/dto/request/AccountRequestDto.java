package com.example.dto.request;

import com.example.enums.AccountStatus;
import com.example.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountRequestDto {
    private String accountNumber;
    private String username;
    private String password;
    private UserRole role;
    private String currency;
    private BigDecimal balance;
    private AccountStatus status;
}
