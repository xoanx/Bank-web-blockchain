package com.example.mapper;

import com.example.dto.detail.AccountDetailResponseDto;
import com.example.dto.lite.AccountLiteResponseDto;
import com.example.dto.request.AccountRequestDto;
import com.example.entity.Account;
import com.example.entity.Person;

public class AccountMapper {
    //map to Detail Response
    public static AccountDetailResponseDto accountMapToDetail (Account account) {
        if(account == null) { return null; }
        return AccountDetailResponseDto.builder()
                .idAccount (account.getIdAccount ())
                .accountNumber (account.getAccountNumber ())
                .username (account.getUsername ())
                .balance (account.getBalance ())
                .currency (account.getCurrency ())
                .accountStatus (account.getAccountStatus ())
                .role (account.getRole ())
                .logInAt (account.getLogInAt ())
                .logOutAt (account.getLogOutAt ())
                .person (PersonMapper.personMapToLite (account.getPerson ()))
                .build();
    }
    //map to Lite Response
    public static AccountLiteResponseDto accountMapToLite (Account account) {
        if(account == null) { return null; }
        return AccountLiteResponseDto.builder()
                .idAccount(account.getIdAccount())
                .accountNumber(account.getAccountNumber())
                .username(account.getUsername())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .build();
    }
    //map to Entity
    public static Account mapToAccount(AccountRequestDto accountRequestDto, Person person) {
        if(accountRequestDto == null) { return null; }
        return Account.builder ()
                .accountNumber(accountRequestDto.getAccountNumber())
                .username (accountRequestDto.getUsername())
                .balance (accountRequestDto.getBalance ())
                .currency (accountRequestDto.getCurrency ())
                .role (accountRequestDto.getRole ())
                .accountStatus (accountRequestDto.getStatus ())
                .person (person)
                .build();
    }
}
