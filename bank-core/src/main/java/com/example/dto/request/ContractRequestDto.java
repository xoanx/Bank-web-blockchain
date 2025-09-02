package com.example.dto.request;

import com.example.enums.ContractStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ContractRequestDto {
    private String contractAddress;
    private String contractName;
    private String message;
    private ContractStatus status;
    private UUID personId;
    private UUID idContract;
    private UUID accountId;
}
