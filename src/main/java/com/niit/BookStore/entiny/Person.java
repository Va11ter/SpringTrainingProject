package com.niit.BookStore.entiny;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return String.format("Person[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    public Long getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getLastName(){
        return lastName;
    }
}
