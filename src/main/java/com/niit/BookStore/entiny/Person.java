package com.niit.BookStore.entiny;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "person")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"address", "wishListItems"})
@EqualsAndHashCode(exclude = {"address", "wishListItems"}, callSuper = true)
public class Person extends EntityBase {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private Integer bonus;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name="address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "wish_list",
            joinColumns = @JoinColumn(name="person_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> wishListItems;

}
