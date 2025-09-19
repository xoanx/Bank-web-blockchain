package com.example.service;

import com.example.dto.detail.PersonDetailResponseDto;
import com.example.dto.lite.ContractLiteResponseDto;
import com.example.dto.lite.PersonLiteResponseDto;
import com.example.dto.request.PersonRequestDto;
import com.example.entity.Person;
import com.example.enums.UserRole;

import java.util.List;
import java.util.UUID;

public interface PersonService {
        PersonDetailResponseDto createPerson(PersonRequestDto personRequestDto);
        PersonDetailResponseDto getPersonById( UUID personId);
        List<PersonLiteResponseDto> getAllPersons();
        PersonDetailResponseDto updatedPerson(UUID personId, PersonRequestDto personRequestDto);
        void deletePerson(UUID personId);
        List<PersonDetailResponseDto> searchPerson(PersonRequestDto personRequestDto);
        List<ContractLiteResponseDto> getContractsOfPerson(UUID personId);
        PersonDetailResponseDto findUserByEmail( String username);
        UserRole checkRoleByEmail(String email);
        Person updatedRoleOfPersonByEmail(String email);
}
