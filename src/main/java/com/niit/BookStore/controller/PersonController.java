package com.niit.BookStore.controller;

import com.niit.BookStore.dto.PersonDto;
import com.niit.BookStore.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
@PreAuthorize(value =  "hasRole('USER')")
public class PersonController extends BaseController{
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {this.personService=personService;}

    @GetMapping(value = "/{id}")
    @PreAuthorize(value =  "hasRole('ADMIN')")
    public PersonDto getPersonById(@PathVariable("id") Long id){
        return personService.getPersonById(id);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public PersonDto updatePerson(@PathVariable("id") Long id, @RequestBody PersonDto personDto){
        return personService.updatePerson(id, personDto);
    }

    @GetMapping
    @PreAuthorize(value = "hasRole('ADMIN')")
    public List<PersonDto> getAll(){
        return personService.getAll();
    }

    @GetMapping(value = "/info")
    public PersonDto getPersonInfo(){
        return personService.getPersonById(getUserIdFromSecurityContext());
    }

    @PutMapping(value = "/info")
    public PersonDto updatePersonInfo(@RequestBody PersonDto personDto){
        return personService.updatePerson(getUserIdFromSecurityContext(), personDto);
    }
}
