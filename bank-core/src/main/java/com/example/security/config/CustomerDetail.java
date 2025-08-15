package com.example.security.config;
import com.example.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class CustomerDetail implements UserDetails{
    private final Account account;

    //Get User 's authorities
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority (account.getRole().name())
        );
    }

    //Get account informations
    @Override
    public String getPassword() {
        return account.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return account.getAccountStatus() != Account.Status.SUSPENDED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return account.getAccountStatus() == Account.Status.ACTIVE;
    }
}
