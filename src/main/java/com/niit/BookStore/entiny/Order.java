package com.niit.BookStore.entiny;

import com.niit.BookStore.entiny.enums.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "`order`")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"person", "address", "orderItems"})
@EqualsAndHashCode(exclude = {"person", "address", "orderItems"})
public class Order extends EntityBase {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    @Column(length = 10, precision = 2)
    private BigDecimal total;

    @Column(name = "placed_on")
    private LocalDateTime placedOn;

}

