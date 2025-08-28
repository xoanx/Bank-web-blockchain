package com.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BeneficiaryRequestDto {
    private UUID ownerAccountId;
    private String ownerAccountName;
    private String beneficiaryAccountNumber;
    private String bankCode;
    private String aliasName;
}
