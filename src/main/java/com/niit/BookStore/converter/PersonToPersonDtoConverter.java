package com.niit.BookStore.converter;

import com.niit.BookStore.dto.PersonDto;
import com.niit.BookStore.entiny.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonToPersonDtoConverter implements Converter<Person, PersonDto> {

    @Override
    public PersonDto convert(Person person) {
        return PersonDto.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmail()).build();
    }
}
