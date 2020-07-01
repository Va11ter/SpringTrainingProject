package com.niit.BookStore.service.impl;

import com.niit.BookStore.dto.CartDto;
import com.niit.BookStore.entiny.Cart;
import com.niit.BookStore.exception.ItemNotFoundException;
import com.niit.BookStore.repository.CartRepository;
import com.niit.BookStore.service.CartService;
import com.niit.BookStore.service.CustomConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "Cart not found";
    private CartRepository cartRepository;
    private CustomConversionService conversionService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CustomConversionService conversionService) {
        this.cartRepository = cartRepository;
        this.conversionService = conversionService;
    }

    @Override
    public CartDto getCartById(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        return conversionService.convert(cart, CartDto.class);
    }

    @Override
    public CartDto createCart(CartDto cartDto) {
        Cart cart = conversionService.convert(cartDto, Cart.class);
        cartRepository.save(cart);
        return conversionService.convert(cart, CartDto.class);
    }

    @Override
    public CartDto updateCart(Long id, CartDto cartDto) {
        Cart cart = cartRepository.findById(cartDto.getId()).orElseThrow(
                ()-> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        cart.setItems(cartDto.getItems());
        cart.setPerson(cartDto.getPerson());
        return conversionService.convert(cart, CartDto.class);
    }

    @Override
    public void deleteCart(Long id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
        }else{
            throw new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public List<CartDto> getAll() {
        List<Cart> carts = cartRepository.findAll();
        return carts
                .stream()
                .map((Cart cart) -> conversionService.convert(cart, CartDto.class))
                .collect(Collectors.toList());
    }

}
