package com.example.mapper;

import com.example.dto.AccountDto;
import com.example.entity.Account;

public class AccountMapper {
    public static AccountDto mapToAccountDto(Account account) {
        return new AccountDto (
                account.getAccountNumber (),
                account.getLogInAt (),
                account.getLogOutAt (),
                account.getAccountStatus (),
                account.getRole ()
        );
    }
    public static Account mapToAccount(AccountDto accountDto) {
        return Account.builder ()
                .accountNumber(accountDto.getAccountNumber())
                .logInAt(accountDto.getLogInAt())
                .logOutAt(accountDto.getLogOutAt())
                .AccountStatus(accountDto.getAccountStatus())
                .role(accountDto.getRole())
                .build();
    }
}
