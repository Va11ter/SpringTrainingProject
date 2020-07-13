package com.niit.BookStore.converter;


import com.niit.BookStore.dto.SignUpDto;
import com.niit.BookStore.entiny.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SignUpDtoToPersonConverter implements Converter<SignUpDto, Person> {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignUpDtoToPersonConverter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Person convert(SignUpDto source) {
        String email = source.getEmail();
        if(Objects.nonNull(email)){
            email = email.toLowerCase();
        }
        return Person.builder()
                .email(email)
                .password(source.getPassword())
                .build();
    }
}