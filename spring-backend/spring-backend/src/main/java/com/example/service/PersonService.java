package com.example.service;

import com.example.dto.PersonDto;

import java.util.List;
import java.util.UUID;

public interface PersonService {
        PersonDto createPerson(PersonDto personDto);
        List<PersonDto> getAllPersons();
        PersonDto getPersonById(UUID idPerson);
        PersonDto updatePerson(UUID idPerson, PersonDto personDto);
        void deletePerson(UUID idPerson);
}
