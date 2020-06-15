package com.niit.BookStore.converter;

import com.niit.BookStore.dto.CartDto;
import com.niit.BookStore.entiny.Cart;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CartToCartDtoConverter implements Converter<Cart, CartDto> {
    @Override
    public CartDto convert(Cart source) {
        CartDto target = new CartDto();
        target.setPersonId(source.getPersonId());
        target.setItems(source.getItems());
        target.setId(source.getId());
        return target;
    }
}
