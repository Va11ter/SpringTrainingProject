package com.niit.BookStore.service;

import com.niit.BookStore.dto.CartDto;
import com.niit.BookStore.entiny.Cart;

import java.util.List;

public interface CartService {
    CartDto getCartById(Long id);
    CartDto getPersonCartByPersonId(Long person_id);
    CartDto updateCart(Long id, CartDto cartDto);
    void clearCart(Long person_id);
    List<CartDto> getAll();
    CartDto addItem(Long person_id, Long item_id);
}
