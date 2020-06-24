package com.niit.BookStore.entiny;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "description")
public class Item extends EntityBase{
    private String name;
    @Column(length = 10, precision = 2)
    private BigDecimal price;
    private String description;
    private Integer count;
}
