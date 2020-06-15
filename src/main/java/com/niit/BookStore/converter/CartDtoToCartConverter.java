package com.niit.BookStore.converter;

import com.niit.BookStore.dto.CartDto;
import com.niit.BookStore.entiny.Cart;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CartDtoToCartConverter implements Converter<CartDto, Cart> {
    @Override
    public Cart convert(CartDto source) {
        Cart target = new Cart();
        target.setPersonId(source.getPersonId());
        target.setItems(source.getItems());
        return target;
    }
}
