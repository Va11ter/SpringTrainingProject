package com.niit.BookStore.entiny;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "cart")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "items")
public class Cart extends EntityBase{
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="cart_item",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> items;
}