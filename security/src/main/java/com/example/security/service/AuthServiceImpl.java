package com.example.security.service;

import com.example.entity.Account;
import com.example.entity.Person;
import com.example.enums.AccountStatus;
import com.example.enums.UserRole;
import com.example.repository.AccountRepository;
import com.example.repository.PersonRepository;
import com.example.security.dto.AuthResponseDto;
import com.example.security.dto.LoginRequestDto;
import com.example.security.dto.RegisterRequestDto;
import com.example.security.dto.TokenRefreshRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PersonRepository personRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponseDto register(RegisterRequestDto req) {
        Person person = Person.builder()
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .email(req.getEmail())
                .phoneNumber(req.getPhoneNumber())
                .address(req.getAddress())
                .taxIdentificationNumber(req.getTaxIdentificationNumber())
                .role(UserRole.CLIENT)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        personRepository.save(person);

        Account account = Account.builder()
                .username(req.getUsername())
                .passwordHash(passwordEncoder.encode(req.getPassword()))
                .currency(req.getCurrency())
                .balance(BigDecimal.ZERO)
                .accountStatus(AccountStatus.ACTIVE)
                .tokenVersion(0)
                .person(person)
                .build();
        accountRepository.save(account);

        return issueTokens(account);
    }

    public AuthResponseDto login(LoginRequestDto req) {
        Account account = accountRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (!passwordEncoder.matches(req.getPassword(), account.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        return issueTokens(account);
    }

    public void logout(UUID accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setTokenVersion(account.getTokenVersion() + 1);
        accountRepository.save(account);
    }

    public AuthResponseDto refreshToken(TokenRefreshRequest tokenRefreshRequest) {
        if (!jwtService.validateToken(tokenRefreshRequest.getRefreshToken ())) {
            throw new RuntimeException("Invalid refresh token");
        }
        String username = jwtService.getClaims(tokenRefreshRequest.getRefreshToken ()).getSubject();
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return issueTokens(account);
    }

    private AuthResponseDto issueTokens(Account account) {
        String access = jwtService.generateAccessToken(account);
        String refresh = jwtService.generateRefreshToken(account);
        return AuthResponseDto.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .role(account.getPerson().getRole().name())
                .accountId(account.getIdAccount())
                .personId(account.getPerson().getIdPerson())
                .build();
    }
}
