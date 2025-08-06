package com.example.service.impl;

import com.example.dto.PersonDto;
import com.example.entity.Person;
import com.example.mapper.PersonMapper;
import com.example.repository.PersonRepository;
import com.example.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public List<PersonDto> getAllPersons() {
        return personRepository.findAll().stream()
                .map(PersonMapper::mapToPersonDto)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto getPersonById(UUID idPerson) {
        Person person = personRepository.findById(idPerson)
                .orElseThrow(() -> new RuntimeException("Person not found"));
        return PersonMapper.mapToPersonDto(person);
    }

    @Override
    public PersonDto updatePerson(UUID idPerson, PersonDto personDto) {
        Person existingPerson = personRepository.findById(idPerson)
                .orElseThrow(() -> new RuntimeException("Person not found"));
        Person updatedPerson = PersonMapper.mapToPerson(personDto);
        updatedPerson.setIdPerson(existingPerson.getIdPerson());
        updatedPerson.setCreatedAt(existingPerson.getCreatedAt());
        return PersonMapper.mapToPersonDto(personRepository.save(updatedPerson));
    }

    @Override
    public void deletePerson(UUID idPerson) {
        if (!personRepository.existsById(idPerson)) {
            throw new RuntimeException("Person not found");
        }
        personRepository.deleteById(idPerson);
    }

    @Override
    public PersonDto createPerson(PersonDto personDto) {
        Person person = PersonMapper.mapToPerson(personDto);
        return PersonMapper.mapToPersonDto(personRepository.save(person));
    }
}
