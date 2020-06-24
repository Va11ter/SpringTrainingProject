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
@ToString(exclude = {"address"})
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
    private Set<Item> items;

}
