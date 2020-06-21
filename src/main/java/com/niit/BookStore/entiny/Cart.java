package com.niit.BookStore.entiny;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "cart")
public class Cart extends EntityBase{
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="cart_item",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> items;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> item) {
        this.items = item;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
}