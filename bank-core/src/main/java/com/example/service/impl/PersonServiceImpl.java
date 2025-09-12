package com.example.service.impl;

import com.example.dto.detail.PersonDetailResponseDto;
import com.example.dto.lite.ContractLiteResponseDto;
import com.example.dto.lite.PersonLiteResponseDto;
import com.example.dto.request.ContractRequestDto;
import com.example.dto.request.PersonRequestDto;
import com.example.entity.Account;
import com.example.entity.Contract;
import com.example.entity.Person;
import com.example.mapper.ContractMapper;
import com.example.mapper.PersonMapper;
import com.example.repository.PersonRepository;
import com.example.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public PersonDetailResponseDto createPerson(PersonRequestDto personRequestDto) {
        Person person = PersonMapper.mapToPerson (personRequestDto,List.of (),List.of ());
        person.setCreatedAt (LocalDateTime.now ());
        person.setUpdatedAt (LocalDateTime.now ());
        Person savedPerson = personRepository.save(person);
        return PersonMapper.personMapToDetail (savedPerson, null,null);
    }
    @Override
    public PersonDetailResponseDto getPersonById(UUID personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow (()-> new ExpressionException ("Person not found"));
        return PersonMapper.personMapToDetail (person, null,null);
    }
    @Override
    public List<PersonLiteResponseDto> getAllPersons(){
        return personRepository.findAll ()
                .stream ()
                .map (PersonMapper::personMapToLite)
                .toList ();
    }
    @Override
    public PersonDetailResponseDto updatedPerson(UUID personId, PersonRequestDto personRequestDto){
        Person person = personRepository.findById (personId)
                .orElseThrow (()-> new ExpressionException ("Person not found"));
        //field request
        person.setFirstName (personRequestDto.getFirstName ());
        person.setLastName (personRequestDto.getLastName ());
        person.setEmail (personRequestDto.getEmail ());
        person.setBirthDate (personRequestDto.getBirthDate ());
        person.setPhoneNumber (personRequestDto.getPhoneNumber ());
        person.setAddress (personRequestDto.getAddress ());
        person.setTaxIdentificationNumber (personRequestDto.getTaxIdentificationNumber ());
        person.setUpdatedAt (LocalDateTime.now ());
        person.setRole (personRequestDto.getRole ());
        Person savedPerson = personRepository.save(person);
        return PersonMapper.personMapToDetail (savedPerson, null,null);
    }
    @Override
    public void deletePerson(UUID personId) {
        if (!personRepository.existsById(personId)) {
            throw new RuntimeException ("Person not found");
        }
        personRepository.deleteById(personId);
    }
    @Override
    public List<PersonDetailResponseDto> searchPerson(PersonRequestDto personRequestDto) {
        List<Person> persons = personRepository.findAll ()
                .stream ()
                .filter (p -> (personRequestDto.getFirstName () == null || p.getFirstName ().equalsIgnoreCase (personRequestDto.getFirstName ())))
                .filter (p -> (personRequestDto.getLastName () == null || p.getLastName ().equalsIgnoreCase (personRequestDto.getLastName ())))
                .filter (p -> (personRequestDto.getEmail () == null || p.getEmail ().equalsIgnoreCase (personRequestDto.getEmail ())))
                .filter (p -> (personRequestDto.getPhoneNumber () == null || p.getPhoneNumber ().equalsIgnoreCase (personRequestDto.getPhoneNumber ())))
                .filter (p -> (personRequestDto.getTaxIdentificationNumber () == null || p.getTaxIdentificationNumber ().equalsIgnoreCase (personRequestDto.getTaxIdentificationNumber ())))
                .toList ();
        return persons.stream ()
                .map (p -> PersonMapper.personMapToDetail (p, null,null))
                .toList ();
    }
    @Override
    public List<ContractLiteResponseDto> getContractsOfPerson(UUID personId) {
        Person person = personRepository.findById (personId)
                .orElseThrow (()-> new RuntimeException ("Person not found"));
        return Optional.ofNullable (person.getContracts ())
                .orElse (List.of ())
                .stream ()
                .map (ContractMapper::contractMapToLite)
                .toList ();
    }
    @Override
    public PersonDetailResponseDto findUserByEmail(String username) {
        Person person = personRepository.findByEmail (username)
                .orElseThrow (()-> new ExpressionException ("Person not found"));
        return PersonMapper.personMapToDetail (person, null,null);
    }
}
