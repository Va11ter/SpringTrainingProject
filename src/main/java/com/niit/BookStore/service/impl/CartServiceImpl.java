package com.niit.BookStore.service.impl;

import com.niit.BookStore.dto.CartDto;
import com.niit.BookStore.entiny.Cart;
import com.niit.BookStore.entiny.Item;
import com.niit.BookStore.entiny.Person;
import com.niit.BookStore.exception.ItemNotFoundException;
import com.niit.BookStore.repository.CartRepository;
import com.niit.BookStore.repository.ItemRepository;
import com.niit.BookStore.repository.PersonRepository;
import com.niit.BookStore.service.CartService;
import com.niit.BookStore.service.CustomConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "Something went wrong, please contact our support.";
    private final PersonRepository personRepository;
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final CustomConversionService conversionService;

    @Autowired
    public CartServiceImpl(PersonRepository personRepository, CartRepository cartRepository, ItemRepository itemRepository, CustomConversionService conversionService) {
        this.personRepository = personRepository;
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.conversionService = conversionService;
    }

    @Override
    public CartDto getCartById(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        return conversionService.convert(cart, CartDto.class);
    }

    @Override
    public CartDto getPersonCartByPersonId(Long person_id){
        return conversionService.convert(getPersonCart(person_id), CartDto.class);
    }

    private Cart createCart(Long person_id) {
        Cart cart = new Cart();
        Person person = personRepository.findById(person_id).orElseThrow(() -> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        cart.setPerson(person);
        cartRepository.save(cart);
        return cart;
    }

    private Cart getPersonCart(Long person_id){
        Cart cart = cartRepository.findCartByPerson_Id(person_id);
        if (Objects.isNull(cart)) {
            cart = createCart(person_id);
        }
        return cart;
    }

    @Override
    public CartDto updateCart(Long id, CartDto cartDto) {
        Cart cart = cartRepository.findById(cartDto.getId()).orElseThrow(
                ()-> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        return conversionService.convert(cart, CartDto.class);
    }

    @Override
    public void clearCart(Long person_id){
        Cart cart = getPersonCart(person_id);
        cart.getItems().clear();
    }

    @Override
    public List<CartDto> getAll() {
        List<Cart> carts = cartRepository.findAll();
        return carts
                .stream()
                .map((Cart cart) -> conversionService.convert(cart, CartDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CartDto addItem(Long person_id, Long item_id){
        if(Objects.isNull(person_id) || Objects.isNull(item_id)){
            throw  new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE);
        }
        Cart cart = getPersonCart(person_id);
        Item item = itemRepository.findById(item_id).orElseThrow(
                () -> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        cart.addItem(item);
        return conversionService.convert(cart, CartDto.class);
    }
}
