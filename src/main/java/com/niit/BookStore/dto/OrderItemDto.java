package com.niit.BookStore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto implements Serializable {
    private Long id;
    @JsonProperty("item")
    private ItemDto itemDto;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("count")
    private Integer count;
}
