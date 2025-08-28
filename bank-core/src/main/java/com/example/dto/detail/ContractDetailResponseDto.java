package com.example.dto.detail;

import com.example.dto.lite.PersonLiteResponseDto;
import com.example.enums.ContractStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data@Builder
public class ContractDetailResponseDto {
    private UUID idContract;
    private String contractAddress;
    private String contractName;
    private String message;
    private ContractStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private PersonLiteResponseDto personDetail;
}
