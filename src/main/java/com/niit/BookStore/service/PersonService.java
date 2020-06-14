package com.niit.BookStore.service;

import com.niit.BookStore.dto.PersonDto;

import java.util.List;

public interface PersonService {
    PersonDto getPersonById(Long id);
    PersonDto createPerson(PersonDto personDto);
    PersonDto updatePerson(Long id, PersonDto personDto);
    void deletePerson(Long id);
    List<PersonDto> getAll();
}
