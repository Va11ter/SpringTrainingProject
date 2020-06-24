package com.niit.BookStore.converter;

import com.niit.BookStore.dto.CartDto;
import com.niit.BookStore.entiny.Cart;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CartToCartDtoConverter implements Converter<Cart, CartDto> {
    @Override
    public CartDto convert(Cart source) {
        return CartDto.builder()
                .id(source.getId())
                .items(source.getItems())
                .person(source.getPerson())
                .build();
    }
}
