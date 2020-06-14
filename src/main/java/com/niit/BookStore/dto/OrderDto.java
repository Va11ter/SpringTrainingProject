package com.niit.BookStore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.niit.BookStore.entiny.Item;
import com.niit.BookStore.entiny.Person;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class OrderDto implements Serializable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("person_id")
    private Person personId;
    @JsonProperty("items")
    private List<Item> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item){
        this.items.add(item);
    }
}
