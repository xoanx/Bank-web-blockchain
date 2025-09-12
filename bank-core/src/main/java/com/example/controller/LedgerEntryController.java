package com.example.controller;

import com.example.dto.detail.LedgerEntryDetailResponseDto;
import com.example.dto.request.LedgerEntryRequestDto;
import com.example.service.LedgerEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ledger-entries")
@RequiredArgsConstructor
public class LedgerEntryController {

    private final LedgerEntryService ledgerEntryService;

    @PostMapping
    public ResponseEntity<LedgerEntryDetailResponseDto> createLedgerEntry(LedgerEntryRequestDto ledgerEntryRequestDto) {
        return ResponseEntity.ok (ledgerEntryService.createEntry (ledgerEntryRequestDto));
    }

    @PostMapping("/search")
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK')")
            public ResponseEntity<List<LedgerEntryDetailResponseDto>> searchLedgerEntry(
            @RequestBody LedgerEntryRequestDto ledgerEntryRequestDto){
        return ResponseEntity.ok (ledgerEntryService.searchEntries (ledgerEntryRequestDto));
    }

    @GetMapping
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK')")
    public ResponseEntity<List<LedgerEntryDetailResponseDto>> getLedgerEntries(){
        return ResponseEntity.ok (ledgerEntryService.getAllEntries ());
    }

    @GetMapping("/transaction/{transactionId}")
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK')")
    public ResponseEntity<List<LedgerEntryDetailResponseDto>> getLedgerEntry(@PathVariable UUID transactionId){
        return ResponseEntity.ok (ledgerEntryService.getEntriesByTransaction (transactionId));
    }

    @GetMapping("/account/{accountId}")
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK')")
    public ResponseEntity<List<LedgerEntryDetailResponseDto>> getEntriesByAccount(@PathVariable UUID accountId){
        return ResponseEntity.ok (ledgerEntryService.getEntriesByAccount (accountId));
    }
}
