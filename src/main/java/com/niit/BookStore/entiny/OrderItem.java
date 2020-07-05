package com.niit.BookStore.entiny;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"order", "item"})
@EqualsAndHashCode(exclude = {"order", "item"}, callSuper = true)
public class OrderItem extends EntityBase{
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;
    @Column(name = "item_count")
    private Integer count;
    @Column(name = "item_price", length = 10, precision = 2)
    private BigDecimal price;

}
