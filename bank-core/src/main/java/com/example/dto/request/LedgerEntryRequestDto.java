package com.example.dto.request;

import com.example.enums.EntryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LedgerEntryRequestDto {
    private EntryType entryType;
    private BigDecimal amount;
    private UUID accountId;
    private UUID transactionId;
}
