package com.example.controller;

import com.example.dto.detail.AccountDetailResponseDto;
import com.example.dto.request.AccountRequestDto;
import com.example.entity.Account;
import com.example.entity.Person;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import com.example.repository.PersonRepository;
import com.example.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final PersonRepository personRepository;
    private final AccountRepository accountRepository;
    private final PersonService personService;

    @PostMapping("/person/{personId}")
    @PreAuthorize("hasAnyRole('ADMIN','BANK','CLIENT')")
    public ResponseEntity<AccountDetailResponseDto> createAccount(
            @PathVariable UUID personId,
            @RequestBody AccountRequestDto accountRequestDto) {

        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Person currentPerson = personService.checkRole (currentUsername);
    //viet 1 service tim role nguoi dung hien tai bang username
        if (accountRequestDto.getgetRole ().equals("CLIENT") &&        //check role
                !currentPerson.getId().equals(personId)) {
            throw new AccessDeniedException("CLIENT chỉ được phép tạo account cho chính mình");
        }

        return ResponseEntity.ok(accountService.createAccount(accountRequestDto, personId));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','BANK','STAFF')")
    public ResponseEntity<List<AccountDetailResponseDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @PostMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','BANK','STAFF')")
    public ResponseEntity<List<AccountDetailResponseDto>> searchAccounts(
            @RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.ok(accountService.searchAccount(accountRequestDto));
    }

    @PutMapping("/{accountId}")
    @PreAuthorize("hasAnyRole('ADMIN','BANK')")
    public ResponseEntity<AccountDetailResponseDto> updateAccount(
            @PathVariable UUID accountId,
            @RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.ok(accountService.updateAccount(accountId, accountRequestDto));
    }

    @DeleteMapping("/{accountId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{accountId}/active")
    @PreAuthorize("hasAnyRole('ADMIN','BANK','STAFF')")
    public ResponseEntity<Void> activateAccount(@PathVariable UUID accountId) {
        accountService.activateAccount(accountId);
        return ResponseEntity.noContent().build();
    }
}
