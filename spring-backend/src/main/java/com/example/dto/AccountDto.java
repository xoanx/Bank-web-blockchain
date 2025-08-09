package com.example.dto;

import lombok.*;
import java.time.LocalDateTime;
import com.example.entity.Account.UserRole;
import com.example.entity.Account.Status;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AccountDto {
    private String accountNumber;
    private LocalDateTime logInAt;
    private LocalDateTime logOutAt;
    private Status accountStatus;
    private UserRole role;
}
