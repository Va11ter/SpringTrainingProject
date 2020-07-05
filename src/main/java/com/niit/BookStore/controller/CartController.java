package com.niit.BookStore.controller;

import com.niit.BookStore.dto.CartBonusDto;
import com.niit.BookStore.dto.CartDto;
import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.dto.PromoAddToCartDto;
import com.niit.BookStore.entiny.Cart;
import com.niit.BookStore.entiny.Item;
import com.niit.BookStore.exception.AddBonusesToCartException;
import com.niit.BookStore.exception.AddPromoToCartException;
import com.niit.BookStore.exception.AddressNotSpecifiedAppException;
import com.niit.BookStore.exception.CartIsEmptyAppException;
import com.niit.BookStore.service.CartService;
import com.niit.BookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final OrderService orderService;

    @Autowired
    public CartController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping
    public CartDto getPersonCart(@RequestHeader(name = "user_id") Long person_id){
        return cartService.getPersonCartByPersonId(person_id);
    }

    @PutMapping
    public CartDto updateCart(@PathVariable("id") Long id, @RequestBody CartDto cartDto){
        return cartService.updateCart(id, cartDto);
    }

    @DeleteMapping
    public void deleteCart(@RequestHeader(name = "user_id") Long person_id){
        cartService.clearCart(person_id);
    }

    @PostMapping("/order")
    public OrderDto makeOrder(@RequestHeader(name = "user_id") Long person_id){
        Cart cart = cartService.getPersonCart(person_id);

        if (Objects.isNull(cart.getPerson().getAddress())) {
            throw new AddressNotSpecifiedAppException();
        }

        Set<Item> cartItems = cart.getItems();
        if (Objects.isNull(cartItems) || cartItems.isEmpty()) {
            throw new CartIsEmptyAppException("Cart is empty. Please put something to you cart, before making order.");
        }

        OrderDto newOrderDto =  orderService.makeOrder(cart);
        cartService.clearCart(cart);
        return newOrderDto;
    }

    @PostMapping(value = "/promo")
    public CartDto addPromoToCart(@RequestHeader(name = "user_id") Long person_id, @RequestBody PromoAddToCartDto promoAddToCartDto){
        if(Objects.isNull(promoAddToCartDto.getPromoCode())){
            throw new AddPromoToCartException("Promo name isn't specified.");
        }
        return cartService.addPromoToCart(person_id, promoAddToCartDto.getPromoCode());
    }

    @DeleteMapping(value = "/promo")
    public CartDto deletePromoFromCard(@RequestHeader(name = "user_id") Long person_id){
        return cartService.deletePromoFromCart(person_id);
    }

    @PostMapping(value = "/bonus")
    public CartBonusDto applyBonuses(@RequestHeader(name = "user_id") Long person_id, @RequestBody CartBonusDto cartBonusDto){
        if(Objects.isNull(cartBonusDto)){
            throw new AddBonusesToCartException("Please specify number of bonuses to apply");
        }
        return cartService.applyBonuses(person_id, cartBonusDto);
    }

    @DeleteMapping(value = "/bonus")
    public void clearAppliedBonuses(@RequestHeader(name = "user_id") Long person_id){
        cartService.clearBonuses(person_id);
    }

    @GetMapping(value = "/bonus")
    public CartBonusDto getAppliedBonuses(@RequestHeader(name = "user_id") Long person_id){
        return cartService.getAppliedBonuses(person_id);
    }
}
