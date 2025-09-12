package com.example.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AuthResponseDto {
    private String accessToken;
    private String refreshToken;
    private String message;
    private String role;
    private UUID accountId;
    private UUID personId;
    private Integer tokenVersion;
}
