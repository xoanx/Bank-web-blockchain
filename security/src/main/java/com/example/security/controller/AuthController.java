package com.example.security.controller;

import com.example.security.dto.AuthResponseDto;
import com.example.security.dto.LoginRequestDto;
import com.example.security.dto.RegisterRequestDto;
import com.example.security.dto.TokenRefreshRequest;
import com.example.security.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto req) {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/logout/{accountId}")
    public ResponseEntity<Void> logout(@PathVariable UUID accountId) {
        authService.logout(accountId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refreshToken(@RequestBody TokenRefreshRequest req) {
        return ResponseEntity.ok(authService.refreshToken(req));
    }
}
