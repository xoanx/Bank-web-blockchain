package com.example.dto;

import com.example.enums.AccountStatus;
import com.example.enums.UserRole;
import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AccountDto {
    private String accountNumber;
    private LocalDateTime logInAt;
    private LocalDateTime logOutAt;
    private AccountStatus accountStatus;
    private UserRole role;
}
