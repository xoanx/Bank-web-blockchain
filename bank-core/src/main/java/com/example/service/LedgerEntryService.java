package com.example.service;

import com.example.dto.detail.LedgerEntryDetailResponseDto;
import com.example.dto.request.LedgerEntryRequestDto;
import com.example.specidication.LedgerSpecification;

import java.util.List;
import java.util.UUID;

public interface LedgerEntryService {
    LedgerEntryDetailResponseDto createEntry(LedgerEntryRequestDto ledgerEntryRequestDto);
    List<LedgerEntryDetailResponseDto> getAllEntries();
    List<LedgerEntryDetailResponseDto> getEntriesByTransaction(UUID transactionId);
    List<LedgerEntryDetailResponseDto> getEntriesByAccount(UUID accountId);
    List<LedgerEntryDetailResponseDto> searchEntries(LedgerEntryRequestDto ledgerEntryRequestDto);
}
