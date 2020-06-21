package com.niit.BookStore.entiny;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
public class Item extends EntityBase{
    private String name;
    @Column(length = 10, precision = 2)
    private BigDecimal price;
    private String description;
    private Integer count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
