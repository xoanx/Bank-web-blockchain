package com.example.controller;

import com.example.dto.detail.AccountDetailResponseDto;
import com.example.dto.request.AccountRequestDto;
import com.example.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/person/{personId}")
    public ResponseEntity<AccountDetailResponseDto> createAccount(
            @PathVariable UUID personId,
            @RequestBody AccountRequestDto accountRequestDto ){
        return ResponseEntity.ok (accountService.createAccount (accountRequestDto, personId));
    }

    @GetMapping
    public ResponseEntity<List<AccountDetailResponseDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @PostMapping("/search")
    public ResponseEntity<List<AccountDetailResponseDto>> searchAccounts(
            @RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.ok (accountService.searchAccount (accountRequestDto));
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<AccountDetailResponseDto> updateAccount(
            @PathVariable UUID accountId,
            @RequestBody AccountRequestDto accountRequestDto){
        return ResponseEntity.ok (accountService.updateAccount (accountId, accountRequestDto));
    }

    @DeleteMapping("/{accountId")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{accountId}/active")
    public ResponseEntity<Void> activateAccount(@PathVariable UUID accountId) {
        accountService.activateAccount(accountId);
        return ResponseEntity.noContent().build();
    }
}
