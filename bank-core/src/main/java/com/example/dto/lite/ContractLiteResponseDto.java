package com.example.dto.lite;

import com.example.enums.ContractStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ContractLiteResponseDto {
    private UUID idContract;
    private String contractName;
    private ContractStatus status;
    private String contractAddress;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private PersonLiteResponseDto person;
    private AccountLiteResponseDto account;
}
