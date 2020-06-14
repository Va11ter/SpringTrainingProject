package com.niit.BookStore.repository;


import com.niit.BookStore.entiny.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {
}
