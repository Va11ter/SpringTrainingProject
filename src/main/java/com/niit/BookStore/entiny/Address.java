package com.niit.BookStore.entiny;

import lombok.*;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;

@Entity
@Table(name="address")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "person")
@EqualsAndHashCode(exclude = "person", callSuper = true)
public class Address extends EntityBase{
    @Column(name = "postal_code")
    private String postalCode;
    private String country;
    private String city;
    private String street;
    private String building;
    private String apartment;
    @Column(name = "info")
    private String additionalInfo;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name="id", referencedColumnName = "address_id")
    private Person person;
}
