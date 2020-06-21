package com.niit.BookStore.entiny;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "orders")
public class Order extends EntityBase {
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "order_item",
            joinColumns=@JoinColumn("order_id"),
            inverseJoinColumns = @JoinColumn("item_id"))
    private Set<Item> item;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    @Column(length = 10, precision = 2)
    private BigDecimal total;

    @Column(name = "placed_on")
    private LocalDateTime placedOn;

    public Person getPersonId() {
        return person;
    }

    public void setPersonId(Person person) {
        this.person = person;
    }

    public Set<Item> getItems() {
        return item;
    }

    public void setItems(Set<Item> item) {
        this.item = item;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}

