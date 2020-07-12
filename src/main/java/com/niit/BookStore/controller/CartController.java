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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/cart")
public class CartController extends BaseController{
    private final CartService cartService;
    private final OrderService orderService;

    @Autowired
    public CartController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping
    public CartDto getPersonCart() {
        return cartService.getPersonCartByPersonId(getUserIdFromSecurityContext());
    }

    @PutMapping
    public CartDto updateCart(@PathVariable("id") Long id, @RequestBody CartDto cartDto) {
        return cartService.updateCart(id, cartDto);
    }

    @DeleteMapping
    public void deleteCart() {
        cartService.clearCart(getUserIdFromSecurityContext());
    }

    @PostMapping("/order")
    public OrderDto makeOrder() {
        Cart cart = cartService.getPersonCart(getUserIdFromSecurityContext());

        if (Objects.isNull(cart.getPerson().getAddress())) {
            throw new AddressNotSpecifiedAppException();
        }

        Set<Item> cartItems = cart.getItems();
        if (Objects.isNull(cartItems) || cartItems.isEmpty()) {
            throw new CartIsEmptyAppException("Cart is empty. Please put something to you cart, before making order.");
        }

        OrderDto newOrderDto = orderService.makeOrder(cart);
        cartService.clearCart(cart);
        return newOrderDto;
    }

    @PostMapping(value = "/promo")
    public CartDto addPromoToCart(@RequestBody PromoAddToCartDto promoAddToCartDto) {

        if (Objects.isNull(promoAddToCartDto.getPromoCode())) {
            throw new AddPromoToCartException("Promo name isn't specified.");
        }
        return cartService.addPromoToCart(getUserIdFromSecurityContext(), promoAddToCartDto.getPromoCode());
    }

    @DeleteMapping(value = "/promo")
    public CartDto deletePromoFromCard() {
        return cartService.deletePromoFromCart(getUserIdFromSecurityContext());
    }

    @PostMapping(value = "/bonus")
    public CartBonusDto applyBonuses(@RequestBody CartBonusDto cartBonusDto) {
        if (Objects.isNull(cartBonusDto)) {
            throw new AddBonusesToCartException("Please specify number of bonuses to apply");
        }
        return cartService.applyBonuses(getUserIdFromSecurityContext(), cartBonusDto);
    }

    @DeleteMapping(value = "/bonus")
    public void clearAppliedBonuses() {
        cartService.clearBonuses(getUserIdFromSecurityContext());
    }

    @GetMapping(value = "/bonus")
    public CartBonusDto getAppliedBonuses() {
        return cartService.getAppliedBonuses(getUserIdFromSecurityContext());
    }
}
