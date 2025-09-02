package com.example.mapper;

import com.example.dto.detail.AccountDetailResponseDto;
import com.example.dto.detail.PersonDetailResponseDto;
import com.example.dto.lite.AccountLiteResponseDto;
import com.example.dto.lite.ContractLiteResponseDto;
import com.example.dto.lite.PersonLiteResponseDto;
import com.example.dto.request.PersonRequestDto;
import com.example.entity.Account;
import com.example.entity.Contract;
import com.example.entity.Person;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PersonMapper {

    //map to Lite
    public static PersonLiteResponseDto personMapToLite(Person person) {
        if(person == null){ return null; }
        return PersonLiteResponseDto.builder()
                .idPerson (person.getIdPerson ())
                .firstName (person.getFirstName ())
                .lastName (person.getLastName ())
                .email (person.getEmail ())
                .build ();
    }
    //map to detail
    public static PersonDetailResponseDto personMapToDetail(Person person, Account acounts, Contract contracts) {
        if(person == null){ return null; }
        List<AccountLiteResponseDto> accountLiteResponseDtos = Optional.ofNullable (person.getAccounts ())
                .orElse (List.of ())
                .stream ()
                .map(AccountMapper::accountMapToLite)
                .toList ();
        List<ContractLiteResponseDto> contractLiteResponseDtos = Optional.ofNullable (person.getContracts ())
                .orElse (List.of ())
                .stream ()
                .map (ContractMapper::contractMapToLite)
                .toList ();
        return PersonDetailResponseDto.builder ()
                .idPerson (person.getIdPerson ())
                .firstName (person.getFirstName ())
                .lastName (person.getLastName ())
                .birthDate (person.getBirthDate ())
                .email (person.getEmail ())
                .phoneNumber (person.getPhoneNumber ())
                .address (person.getAddress ())
                .taxIdentificationNumber (person.getTaxIdentificationNumber ())
                .createdAt (person.getCreatedAt ())
                .updatedAt (person.getUpdatedAt ())
                .expiredAt (person.getExpiredAt ())
                .accounts (accountLiteResponseDtos)
                .contracts (contractLiteResponseDtos)
                .build ();
    }
    //map to Entity
    public static Person mapToPerson(PersonRequestDto personRequestDto, List<Account> accounts, List<Contract> contracts) {
        if(personRequestDto == null){ return null; }
        return Person.builder ()
                .firstName (personRequestDto.getFirstName ())
                .lastName (personRequestDto.getLastName ())
                .birthDate (personRequestDto.getBirthDate ())
                .email (personRequestDto.getEmail ())
                .phoneNumber (personRequestDto.getPhoneNumber ())
                .address (personRequestDto.getAddress ())
                .taxIdentificationNumber (personRequestDto.getTaxIdentificationNumber ())
                .createdAt (LocalDateTime.now ())
                .updatedAt (LocalDateTime.now ())
                .accounts (accounts)
                .contracts (contracts)
                .build ();
    }
}
