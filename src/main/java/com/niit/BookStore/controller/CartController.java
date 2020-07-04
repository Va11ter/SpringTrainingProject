package com.niit.BookStore.controller;

import com.niit.BookStore.dto.CartDto;
import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
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
        return new OrderDto();
    }

}
