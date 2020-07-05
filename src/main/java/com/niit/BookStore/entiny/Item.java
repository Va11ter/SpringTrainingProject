package com.niit.BookStore.entiny;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "item")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"description", "categories"})
@EqualsAndHashCode(exclude = {"description", "categories"}, callSuper = true)
public class Item extends EntityBase{
    private String name;

    @Column(length = 10, precision = 2)
    private BigDecimal price;
    private String description;
    private Integer count;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            mappedBy = "items")
    private Set<Category> categories;
}
