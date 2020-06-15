package com.niit.BookStore.service;

import com.niit.BookStore.dto.CartDto;

import java.util.List;

public interface CartService {
    CartDto getCartById(Long id);
    CartDto createCart(CartDto cartDto);
    CartDto updateCart(Long id, CartDto cartDto);
    void deleteCart(Long id);
    List<CartDto> getAll();
}
