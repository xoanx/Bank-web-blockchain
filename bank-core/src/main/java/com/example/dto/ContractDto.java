package com.example.dto;

import com.example.entity.Contract;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractDto {

    private UUID idContract;
    private String contractAddress;
    private String contractName;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
    private Contract.Status status;
}
