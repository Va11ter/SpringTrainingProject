package com.niit.BookStore.controller;

import com.niit.BookStore.dto.CartDto;
import com.niit.BookStore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping(value = "/{id}")
    public CartDto getCartById(@PathVariable("id") Long id){
        return cartService.getCartById(id);
    }

    @PostMapping
    public CartDto createCart(@RequestBody CartDto cartDto){
        return cartService.createCart(cartDto);
    }

    @PutMapping(value = "/{id}")
    public CartDto updateCart(@PathVariable("id") Long id, @RequestBody CartDto cartDto){
        return cartService.updateCart(id, cartDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCart(@PathVariable Long id){
        cartService.deleteCart(id);
    }

    @GetMapping
    public List<CartDto> getAll(){
        return cartService.getAll();
    }

}
