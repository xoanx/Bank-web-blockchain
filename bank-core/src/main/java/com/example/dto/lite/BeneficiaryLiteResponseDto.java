package com.example.dto.lite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data@Builder
public class BeneficiaryLiteResponseDto {
    private UUID beneficiaryId;
    private String beneficiaryName;
    private String aliasName;
}
