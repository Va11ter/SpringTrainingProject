package com.niit.BookStore.repository;


import com.niit.BookStore.dto.PersonDto;
import com.niit.BookStore.entiny.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;


public interface PersonRepository extends JpaRepository<Person, Long> {
}
