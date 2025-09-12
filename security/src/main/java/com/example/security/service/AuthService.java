package com.example.security.service;

import com.example.entity.Account;
import com.example.security.dto.AuthResponseDto;
import com.example.security.dto.LoginRequestDto;
import com.example.security.dto.RegisterRequestDto;
import com.example.security.dto.TokenRefreshRequest;

import java.util.UUID;

public interface AuthService {
    AuthResponseDto register (RegisterRequestDto registerRequestDto);

    AuthResponseDto login (LoginRequestDto loginRequestDto);

    void logout (UUID accountId);

    AuthResponseDto refreshToken (TokenRefreshRequest tokenRefreshRequest);

//    AuthResponseDto issueTokens (Account account);
}

