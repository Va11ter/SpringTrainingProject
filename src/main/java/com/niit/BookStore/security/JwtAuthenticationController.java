package com.niit.BookStore.security;


import com.niit.BookStore.entiny.Person;
import com.niit.BookStore.exception.SignUpException;
import com.niit.BookStore.repository.PersonRepository;
import com.niit.BookStore.service.CustomConversionService;
import com.niit.BookStore.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@CrossOrigin
@Transactional
public class JwtAuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final PersonRepository personRepository;
    private final CustomConversionService conversionService;

    @Autowired
    public JwtAuthenticationController(
            AuthenticationManager authenticationManager,
            JwtTokenUtil jwtTokenUtil,
            @Qualifier("customUserDetailsService") UserDetailsService userDetailsService,
            PersonRepository personRepository,
            CustomConversionService conversionService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.personRepository = personRepository;
        this.conversionService = conversionService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping(value = "/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        if(Objects.isNull(signUpDto))
            return ResponseEntity.badRequest().build();
        Person person = conversionService.convert(signUpDto, Person.class);
        try {
            personRepository.save(person);
       } catch (DataIntegrityViolationException e){
            throw new SignUpException("Looks like you are already Signed Up please try to login");
        }
        return ResponseEntity.ok("");
    }

    private void authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }
}
