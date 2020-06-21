package com.niit.BookStore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.niit.BookStore.entiny.Item;
import com.niit.BookStore.entiny.Person;

import java.io.Serializable;
import java.util.Set;

public class OrderDto implements Serializable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("person")
    private Person person;
    @JsonProperty("items")
    private Set<Item> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPersonId(Person person) {
        this.person = person;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public void addItem(Item item){
        this.items.add(item);
    }
}
