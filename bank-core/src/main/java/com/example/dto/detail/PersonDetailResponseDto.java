package com.example.dto.detail;

import com.example.dto.lite.AccountLiteResponseDto;
import com.example.dto.lite.ContractLiteResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data@Builder
public class PersonDetailResponseDto {
    private UUID idPerson;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private String address;
    private String taxIdentificationNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime expiredAt;

    private List<AccountLiteResponseDto> accounts;
    private List<ContractLiteResponseDto> contracts;
}
