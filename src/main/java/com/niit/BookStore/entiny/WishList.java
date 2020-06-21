package com.niit.BookStore.entiny;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wish_list")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    //TODO: Find How to name variable plural, without renaming columns
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="wish_list_item")
    private List<Item> item;


    public Long getId() {
        return id;
    }

    public Person getPersonId() {
        return person;
    }

    public void setPersonId(Person person) {
        this.person = person;
    }

    public List<Item> getItems() {
        return item;
    }

    public void setItems(List<Item> item) {
        this.item = item;
    }

    public void addItem(Item item) {
        this.item.add(item);
    }
}