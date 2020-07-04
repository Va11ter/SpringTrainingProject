package com.niit.BookStore.converter;

import com.niit.BookStore.dto.CartDto;
import com.niit.BookStore.entiny.Cart;
import com.niit.BookStore.entiny.Item;
import com.niit.BookStore.service.CustomConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CartDtoToCartConverter implements Converter<CartDto, Cart> {
    private final CustomConversionService customConversionService;

    public CartDtoToCartConverter(CustomConversionService customConversionService) {
        this.customConversionService = customConversionService;
    }

    @Override
    public Cart convert(CartDto source) {
        return Cart.builder()
                .items(customConversionService.convert(source.getItems(), Item.class))
                .build();
    }
}
