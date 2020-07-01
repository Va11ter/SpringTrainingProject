package com.niit.BookStore.service.impl;

import com.niit.BookStore.dto.PersonDto;
import com.niit.BookStore.entiny.Person;
import com.niit.BookStore.exception.ItemNotFoundException;
import com.niit.BookStore.repository.PersonRepository;
import com.niit.BookStore.service.CustomConversionService;
import com.niit.BookStore.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "Person not found";
    private final PersonRepository personRepository;
    private final CustomConversionService conversionService;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, CustomConversionService conversionService) {
        this.personRepository = personRepository;
        this.conversionService = conversionService;
    }

    @Override
    public PersonDto getPersonById(Long id){
        Person person = personRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        return conversionService.convert(person, PersonDto.class);
    }

    @Override
    public PersonDto createPerson(PersonDto personDto) {
        Person person = conversionService.convert(personDto, Person.class);
        personRepository.save(person);
        return conversionService.convert(person, PersonDto.class);
    }

    @Override
    public PersonDto updatePerson(Long id, PersonDto personDto) {
        Person person = personRepository.findById(personDto.getId()).orElseThrow(
                ()-> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        person.setLastName(personDto.getLastName());
        person.setFirstName(personDto.getFirstName());
        person.setEmail(personDto.getEmail());
        return conversionService.convert(person, PersonDto.class);
    }

    @Override
    public void deletePerson(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        }else{
            throw new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public List<PersonDto> getAll() {
        List<Person> persons = personRepository.findAll();
        return persons
                .stream()
                .map((Person person) -> conversionService.convert(person, PersonDto.class))
                .collect(Collectors.toList());
    }


}
