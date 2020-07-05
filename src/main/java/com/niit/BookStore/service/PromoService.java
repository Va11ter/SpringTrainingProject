package com.niit.BookStore.service;

import com.niit.BookStore.dto.PromoDto;
import com.niit.BookStore.entiny.Cart;
import com.niit.BookStore.entiny.Item;
import com.niit.BookStore.entiny.Person;
import com.niit.BookStore.entiny.Promo;

import java.util.List;

public interface PromoService {
    PromoDto getPromoById(Long id);
    Promo getActivePromoByCode(String name);
    PromoDto createPromo(PromoDto promoDto);
    PromoDto updatePromo(Long id, PromoDto promoDto);
    boolean isPromoActive(Long id);
    boolean isPromoActive(Promo promo);
    void deletePromo(Long id);
    List<PromoDto> getAll();
    boolean isApplicableForCart(Promo promo, Cart cart);
    boolean isApplicableForPerson(Promo promo, Person person);
    boolean isApplicableForItem(Promo promo, Item item);
}
