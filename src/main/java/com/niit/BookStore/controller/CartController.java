package com.niit.BookStore.controller;

import com.niit.BookStore.dto.CartDto;
import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.entiny.Cart;
import com.niit.BookStore.entiny.Item;
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

}
