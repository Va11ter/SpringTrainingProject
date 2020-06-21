package com.niit.BookStore.entiny;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person extends EntityBase {
    private String firstName;
    private String lastName;
    private String email;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name="address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "wish_list",
    joinColumns = @JoinColumn(name="person_id"),
    inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> wishListItems;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Item> getWishListItems() {
        return wishListItems;
    }

    public void setWishListItems(Set<Item> wishListItems) {
        this.wishListItems = wishListItems;
    }
}
