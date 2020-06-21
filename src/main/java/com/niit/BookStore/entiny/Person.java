package com.niit.BookStore.entiny;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person extends EntityBase {
    private String firstName;
    private String lastName;
    private String email;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name="address_id", referencedColumnName = "id")
    private Address address;

    @Override
    public String toString() {
        return String.format("Person[id=%s, firstName='%s', lastName='%s']",
                getId(), firstName, lastName);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
