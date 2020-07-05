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
@ToString(exclude = {"items", "promo"})
@EqualsAndHashCode(exclude = {"items", "promo"}, callSuper = true)
public class Cart extends EntityBase{
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="cart_item",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> items;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="promo_id")
    private Promo promo;

    public void addItem(Item item){
        items.add(item);
    }
}