package com.niit.BookStore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoAddToCartDto implements Serializable {
    @JsonProperty("promo_code")
    private String promoCode;
}
