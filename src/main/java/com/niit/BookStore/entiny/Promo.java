package com.niit.BookStore.entiny;

import com.niit.BookStore.entiny.enums.PromoType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "promo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"description", "categories", "person"})
@EqualsAndHashCode(exclude = {"description", "categories", "person"})
public class Promo extends EntityBase{
    private String code;
    private String description;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "is_active")
    private boolean isActive=true;
    // Discount in %
    private Integer discount;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PromoType type;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name="promo_category",
            joinColumns = @JoinColumn(name="promo_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id")
    private Person person;
}
