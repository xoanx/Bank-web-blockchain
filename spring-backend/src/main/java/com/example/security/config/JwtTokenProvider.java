package com.yourpackage.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final Key secretKey;
    private final long validityInMilliseconds;

    // Load key và expiration từ application.properties
    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long validityInMilliseconds
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.validityInMilliseconds = validityInMilliseconds;
    }

    // Tạo token mới
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + validityInMilliseconds);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        logger.info("Generated JWT token for user: {}", username);
        return token;
    }

    // Lấy username từ token
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Kiểm tra token hợp lệ không
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("JWT token đã hết hạn: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT không được hỗ trợ: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("JWT token không hợp lệ: {}", e.getMessage());
        } catch (SignatureException e) {
            logger.error("Chữ ký JWT không hợp lệ: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string trống: {}", e.getMessage());
        }
        return false;
    }
}
