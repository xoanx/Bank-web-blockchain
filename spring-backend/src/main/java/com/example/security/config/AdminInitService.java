package com.example.security.config;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Component
@Service
@RequiredArgsConstructor
public class AdminInitService {
    private final AccountRepository accountRepository;
    @Value ("${app.admin.master-key}")
    private String masterKey;

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;
    private final PasswordEncoder passwordEncoder;


    public String createAdminIfNotExists(String providedKey) {
        if (!masterKey.equals(providedKey)) {
            return "Master key invalid!";
        }

        if (accountRepository.findByUsername(adminUsername).isPresent()) {
            return "✅ Admin đã tồn tại.";
        }

        Account admin = Account.builder()
                .accountNumber("ADMIN-0001")
                .username(adminUsername)
                .passwordHash(passwordEncoder.encode(adminPassword))
                .logInAt(LocalDateTime.now())
                .logOutAt(LocalDateTime.now())
                .accountStatus(Account.Status.ACTIVE)
                .role(Account.UserRole.ADMIN)
                .build();

        accountRepository.save(admin);
        return "🎉 Admin đã được tạo thành công!";
    }
}
