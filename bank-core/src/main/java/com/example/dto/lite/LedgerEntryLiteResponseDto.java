package com.example.dto.lite;

import com.example.enums.EntryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data@Builder
public class LedgerEntryLiteResponseDto {
    private UUID ledgerEntryId;
    private BigDecimal amount;
    private EntryType entryType;
}
