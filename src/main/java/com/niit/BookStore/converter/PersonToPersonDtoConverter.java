package com.niit.BookStore.converter;

import com.niit.BookStore.dto.PersonDto;
import com.niit.BookStore.entiny.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonToPersonDtoConverter implements Converter<Person, PersonDto> {

    @Override
    public PersonDto convert(Person person) {
        PersonDto target = new PersonDto();
        target.setId(person.getId());
        target.setFirstName(person.getFirstName());
        target.setLastName(person.getLastName());
        return target;
    }
}
