package com.example.controller;

import com.example.dto.AccountDto;
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

    @GetMapping("/get-all-account/")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/get-accpunt/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable UUID id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping("/create-new-account/")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto dto) {
        return ResponseEntity.ok(accountService.createAccount(dto));
    }

    @PutMapping("/update-account/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable UUID id, @RequestBody AccountDto dto) {
        return ResponseEntity.ok(accountService.updateAccount(id, dto));
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
