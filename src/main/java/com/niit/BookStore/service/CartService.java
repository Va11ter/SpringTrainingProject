package com.niit.BookStore.service;

import com.niit.BookStore.dto.CartBonusDto;
import com.niit.BookStore.dto.CartDto;
import com.niit.BookStore.entiny.Cart;

import java.util.List;

public interface CartService {
    Cart getPersonCart(Long person_id);
    CartDto getCartById(Long id);
    CartDto getPersonCartByPersonId(Long person_id);
    CartDto updateCart(Long id, CartDto cartDto);
    List<CartDto> getAll();
    CartDto addItem(Long person_id, Long item_id);
    void clearCart(Long person_id);
    void clearCart(Cart cart);

    CartDto addPromoToCart(Long person_id, String promoName);
    CartDto deletePromoFromCart(Long person_id);

    CartBonusDto applyBonuses(Long person_id, CartBonusDto cartBonusDto);
    void clearBonuses(Long person_id);
    CartBonusDto getAppliedBonuses(Long person_id);

}
