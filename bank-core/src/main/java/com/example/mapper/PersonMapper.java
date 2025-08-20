package com.example.mapper;

import com.example.dto.AccountDto;
import com.example.dto.ContractDto;
import com.example.dto.PersonDto;
import com.example.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonMapper {

    public static PersonDto mapToPersonDto(Person person) {
//        List<AccountDto> accountDtos = person.getAccounts () != null
//                ? person.getAccounts ().stream ()
//                    .map (AccountMapper::mapToAccountDto)
//                    .toList ()
//                :null;
//        List<ContractDto> contractDtos = person.getContracts () != null
//                ? person.getContracts ().stream ()
//                    .map (ContractMapper::mapToContractDto)
//                    .toList ()
//                :null;
        List<AccountDto> accountDtos = Optional.ofNullable (person.getAccounts())
                .orElse(List.of ())
                .stream ()
                .map(AccountMapper::mapToAccountDto)
                .toList ();
        List<ContractDto> contractDtos = Optional.ofNullable (person.getContracts ())
                .orElse (List.of ())
                .stream ()
                .map (ContractMapper::mapToContractDto)
                .toList ();
        return PersonDto.builder()
                .idPerson(person.getIdPerson())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .birthDate(person.getBirthDate())
                .email(person.getEmail())
                .phoneNumber(person.getPhoneNumber())
                .address(person.getAddress())
                .taxIdentificationNumber(person.getTaxIdentificationNumber())
                .createdAt(person.getCreatedAt())
                .updatedAt(person.getUpdatedAt())
                .expiredAt(person.getExpiredAt())
                .accounts (accountDtos)
                .contracts (contractDtos)
                .build();
    }

    public static Person mapToPerson(PersonDto personDto) {
        return Person.builder()
                .idPerson(personDto.getIdPerson())
                .firstName(personDto.getFirstName())
                .lastName(personDto.getLastName())
                .birthDate(personDto.getBirthDate())
                .email(personDto.getEmail())
                .phoneNumber(personDto.getPhoneNumber())
                .address(personDto.getAddress())
                .taxIdentificationNumber(personDto.getTaxIdentificationNumber())
                .createdAt(personDto.getCreatedAt())
                .updatedAt(personDto.getUpdatedAt())
                .expiredAt(personDto.getExpiredAt())
                .build();
    }
}
