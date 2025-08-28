package com.example.dto.detail;

import com.example.dto.lite.AccountLiteResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data@Builder
public class BeneficiaryDetailResponseDto {
    private UUID beneficiaryId;
    private String beneficiaryName;
    private String beneficiaryAccountNumber;
    private String bankCode;
    private String aliasName;

    private AccountLiteResponseDto ownerAccount;
}
