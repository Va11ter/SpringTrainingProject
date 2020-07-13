package com.niit.BookStore.service.impl;

import com.niit.BookStore.dto.PersonDto;
import com.niit.BookStore.entiny.Person;
import com.niit.BookStore.exception.ItemNotFoundException;
import com.niit.BookStore.repository.PersonRepository;
import com.niit.BookStore.service.CustomConversionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {
    public static String PERSON_NOT_FOUND_EXCEPTION_MESSAGE = "Person not found";

    @InjectMocks
    private PersonServiceImpl personService;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private CustomConversionService conversionService;

    @Test
    public void testGetPersonByIdPersonNotFound() {
        Mockito.when(personRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ItemNotFoundException.class, () -> personService.getPersonById(1L),
                PERSON_NOT_FOUND_EXCEPTION_MESSAGE);
    }

    @Test
    public void verifyGetPersonByIdFromRepository() {
        Mockito.when(personRepository.findById(any())).thenReturn(Optional.of(new Person()));
        personService.getPersonById(1L);
        Mockito.verify(personRepository).findById(any());
    }

    @Test
    public void verifyGetConvertPerson() {
        Person expectedPerson = new Person();
        PersonDto expectedPersonDto = new PersonDto();
        Mockito.when(personRepository.findById(any())).thenReturn(Optional.of(expectedPerson));
        Mockito.when(conversionService.convert(expectedPerson, PersonDto.class)).thenReturn(expectedPersonDto);
        PersonDto result = personService.getPersonById(1L);
        Mockito.verify(conversionService).convert(expectedPerson, PersonDto.class);
        assertSame(expectedPersonDto, result);
    }

    @Test
    public void testDeletePersonById() {
        Mockito.when(personRepository.existsById(any())).thenReturn(Boolean.TRUE);
        personService.deletePerson(1L);
        Mockito.verify(personRepository).existsById(any());
        Mockito.verify(personRepository).deleteById(any());
    }

    @Test
    public void testDeletePersonByIdPersonNotFound() {
        Mockito.when(personRepository.existsById(any())).thenReturn(Boolean.FALSE);
        assertThrows(ItemNotFoundException.class, () -> personService.deletePerson(1L),
                PERSON_NOT_FOUND_EXCEPTION_MESSAGE);
        Mockito.verify(personRepository).existsById(any());
    }

    @Test
    public void testGetAllPersons(){
        List<Person> findAllUserList = new ArrayList<>(Arrays.asList(new Person(), new Person()));
        Mockito.when(personRepository.findAll()).thenReturn(findAllUserList);
        List<PersonDto> convertedResult = new ArrayList<>(Arrays.asList(new PersonDto(), new PersonDto()));
        Mockito.when(conversionService.convert(findAllUserList, PersonDto.class))
                .thenReturn(convertedResult);
        List<PersonDto> result = personService.getAll();
        assertSame(convertedResult, result);
    }

    @Test
    public void testUpdatePersonByIdPersonNotFound(){
        Mockito.when(personRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ItemNotFoundException.class, () -> personService.updatePerson(1L, new PersonDto()),
                PERSON_NOT_FOUND_EXCEPTION_MESSAGE);
    }

    @Test
    public void testUpdatePersonByIdUpdateAllField(){
        Person originalPerson = new Person();
        PersonDto personForUpdate = PersonDto.builder()
                .id(1L)
                .email("new_email@email.con")
                .firstName("FN")
                .lastName("LN")
                .build();
        Mockito.when(personRepository.findById(any())).thenReturn(Optional.of(originalPerson));

        personService.updatePerson(personForUpdate.getId(), personForUpdate);

        assertEquals(personForUpdate.getEmail(), originalPerson.getEmail());
        assertEquals(personForUpdate.getFirstName(), originalPerson.getFirstName());
        assertEquals(personForUpdate.getLastName(), originalPerson.getLastName());
    }

    @Test
    public void testUpdatePersonByIdGetConvertedPerson(){
        Person originalPerson = new Person();
        PersonDto personForUpdate = PersonDto.builder()
                .id(1L)
                .build();
        PersonDto personAfterUpdate = new PersonDto();
        Mockito.when(personRepository.findById(any())).thenReturn(Optional.of(originalPerson));
        Mockito.when(conversionService.convert(originalPerson, PersonDto.class)).thenReturn(personAfterUpdate);

        PersonDto result = personService.updatePerson(personForUpdate.getId(), personForUpdate);
        Mockito.verify(conversionService).convert(originalPerson, PersonDto.class);
        assertSame(personAfterUpdate, result);

    }


}