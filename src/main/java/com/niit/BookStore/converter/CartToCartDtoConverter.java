package com.niit.BookStore.converter;

import com.niit.BookStore.dto.CartDto;
import com.niit.BookStore.dto.ItemDto;
import com.niit.BookStore.entiny.Cart;
import com.niit.BookStore.service.CustomConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CartToCartDtoConverter implements Converter<Cart, CartDto> {
    private final CustomConversionService customConversionService;

    public CartToCartDtoConverter(CustomConversionService customConversionService) {
        this.customConversionService = customConversionService;
    }

    @Override
    public CartDto convert(Cart source) {
        return CartDto.builder()
                .id(source.getId())
                .items(customConversionService.convert(source.getItems(), ItemDto.class))
                .build();
    }
}
