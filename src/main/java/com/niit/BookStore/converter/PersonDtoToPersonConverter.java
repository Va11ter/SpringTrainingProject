package com.niit.BookStore.converter;

import com.niit.BookStore.dto.PersonDto;
import com.niit.BookStore.entiny.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.lang.annotation.Target;
import java.util.Objects;

@Component
public class PersonDtoToPersonConverter implements Converter<PersonDto, Person> {

    @Override
    public Person convert(PersonDto source) {
        String email = source.getEmail();
        if (Objects.nonNull(email)){
            email = email.toLowerCase();
        }
        return Person.builder()
                .email(email)
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .build();
    }
}