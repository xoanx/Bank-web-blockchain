package com.example.security.controller;

import com.example.entity.Account;
import com.example.enums.AccountStatus;
import com.example.enums.UserRole;
import com.example.repository.AccountRepository;
import com.example.security.config.JwtTokenProvider;
import com.example.security.dto.AuthResponseDto;
import com.example.security.dto.LoginRequestDto;
import com.example.security.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping ("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    //Login
    @PostMapping ("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken (loginRequestDto.getUsername (), loginRequestDto.getPassword ())
            );
            String token = jwtTokenProvider.generateToken(loginRequestDto.getUsername ());
            return ResponseEntity.ok (new AuthResponseDto (token, "Login Success"));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status (HttpStatus.UNAUTHORIZED).body (new AuthResponseDto (null, "Invalid username or password"));
        }
    }

    //Register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequestDto) {
        if (accountRepository.findByUsername(registerRequestDto.getUsername ()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponseDto (null, "Username is already in use"));
        }
        Account a = Account.builder()
                .username(registerRequestDto.getUsername ())
                .passwordHash(passwordEncoder.encode(registerRequestDto.getPassword ()))
                .role(UserRole.CLIENT)
                .accountStatus(AccountStatus.ACTIVE)
                .build();
        accountRepository.save(a);
        return ResponseEntity.ok(new AuthResponseDto (jwtTokenProvider.generateToken(a.getUsername()), "Register Success"));
    }
}
