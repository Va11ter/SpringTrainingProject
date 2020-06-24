package com.niit.BookStore.entiny;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "promo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Promo extends EntityBase{
    private String code;
    private String description;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "is_active")
    private boolean isActive;
    // Discount in %
    private Integer discount;

}
