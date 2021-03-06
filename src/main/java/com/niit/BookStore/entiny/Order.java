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
@ToString(exclude = {"person", "address", "orderItems", "usedBonuses", "earnedBonuses"})
@EqualsAndHashCode(exclude = {"person", "address", "orderItems", "usedBonuses", "earnedBonuses"}, callSuper = true)
public class Order extends EntityBase {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private Set<OrderItem> orderItems;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private OrderState state = OrderState.PLACED;

    @Column(length = 10, precision = 2)
    private BigDecimal total;

    @Column(name = "placed_on")
    private LocalDateTime placedOn;

    @Builder.Default
    @Column(name = "used_bonuses")
    private Integer usedBonuses = 0;

    @Builder.Default
    @Column(name = "earned_bonuses")
    private Integer earnedBonuses = 0;


    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
    }

}

