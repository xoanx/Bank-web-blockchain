package com.example.security.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.security.dto.AuthResponseDto;
import com.example.security.dto.LoginRequestDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value ("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.access-expiration-ms}")
    private long accessExpirationMs;

    @Value("${app.jwt.refresh-expiration-ms}")
    private long refreshExpirationMs;

    private Key key;

    private final AccountRepository accountRepository;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateAccessToken(Account account) {
        return buildToken(account, accessExpirationMs);
    }

    public String generateRefreshToken(Account account) {
        return buildToken(account, refreshExpirationMs);
    }

    private String buildToken(Account account, long expiration) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(account.getUsername())
                .claim("role", account.getPerson().getRole().name())
                .claim("accountId", account.getIdAccount().toString())
                .claim("personId", account.getPerson().getIdPerson().toString())
                .claim("tokenVersion", account.getTokenVersion())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean validateToken(String token) {
        try {
            String username = getClaims (token).getSubject();
            Integer tokenVersion = getClaims (token).get("tokenVersion", Integer.class);
            Account account = accountRepository.findById (getClaims (token).get ("accountId", UUID.class))
                    .orElseThrow(() -> new JwtException("Account not found"));
            return account.getTokenVersion ().equals (tokenVersion);
        } catch (JwtException e) {
            return false;
        }
    }
    public Claims getClaims (String token) {
        return Jwts.parserBuilder ()
                .setSigningKey (key)
                .build ()
                .parseClaimsJws (token)
                .getBody();
    }
    public UserDetails getUserDetails (String token){
        String username = getClaims (token).getSubject ();
        String role = getClaims (token).get("role", String.class);
        String accountId = getClaims (token).get("accountId", String.class);
        String personId = getClaims (token).get("personId", String.class);
        Integer tokenVersion = getClaims (token).get("tokenVersion", Integer.class);
        Account account = accountRepository.findById (getClaims (token).get ("accountId", UUID.class))
                .orElseThrow(() -> new JwtException("Account not found"));
        if(!account.getTokenVersion ().equals (tokenVersion)){
            throw new JwtException("Token version does not match");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(account.getPasswordHash())
                .authorities(List.of(new SimpleGrantedAuthority ("ROLE_" + role)))
                .build();
    }
}
