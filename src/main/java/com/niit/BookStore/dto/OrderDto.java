package com.niit.BookStore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.niit.BookStore.entiny.Item;
import com.niit.BookStore.entiny.OrderItem;
import com.niit.BookStore.entiny.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.plaf.nimbus.State;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto implements Serializable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("person")
    private PersonDto personDto;
    @JsonProperty("items")
    private Set<OrderItemDto> orderItemsDto;
    private String state;
}
