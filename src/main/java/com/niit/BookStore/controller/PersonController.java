package com.niit.BookStore.controller;

import com.niit.BookStore.dto.PersonDto;
import com.niit.BookStore.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController extends BaseController{
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {this.personService=personService;}

    @GetMapping(value = "/{id}")
    public PersonDto getPersonById(@PathVariable("id") Long id){
        return personService.getPersonById(id);
    }

    @PostMapping
    public PersonDto createPerson(@RequestBody PersonDto personDto){
        return personService.createPerson(personDto);
    }

    @PutMapping(value = "/{id}")
    public PersonDto updatePerson(@PathVariable("id") Long id, @RequestBody PersonDto personDto){
        return personService.updatePerson(id, personDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
    }

    @GetMapping
    public List<PersonDto> getAll(){
        return personService.getAll();
    }

    @GetMapping(value = "/info")
    public PersonDto getPersonInfo(){
        return personService.getPersonById(getUserIdFromSecurityContext());
    }

}
