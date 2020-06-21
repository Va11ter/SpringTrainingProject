package com.niit.BookStore.entiny;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "cart")
public class Cart extends EntityBase{
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    //TODO: Find How to name variable plural, without renaming columns
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="cart_item",
            JoinColumns = @JoinColumn("cart_id"),
            inverseJoinColumns = @JoinColumn("item_id"))
    private List<Item> item;

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