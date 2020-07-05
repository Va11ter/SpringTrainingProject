package com.niit.BookStore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.niit.BookStore.entiny.Item;
import com.niit.BookStore.entiny.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto implements Serializable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("items")
    private Set<ItemDto> items;
    @JsonProperty("promo")
    private PromoDto promoDto;
}