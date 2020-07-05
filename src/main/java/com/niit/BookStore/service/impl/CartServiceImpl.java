package com.niit.BookStore.service.impl;

import com.niit.BookStore.dto.CartBonusDto;
import com.niit.BookStore.dto.CartDto;
import com.niit.BookStore.entiny.Cart;
import com.niit.BookStore.entiny.Item;
import com.niit.BookStore.entiny.Person;
import com.niit.BookStore.entiny.Promo;
import com.niit.BookStore.exception.AddBonusesToCartException;
import com.niit.BookStore.exception.AddPromoToCartException;
import com.niit.BookStore.exception.ItemNotFoundException;
import com.niit.BookStore.repository.CartRepository;
import com.niit.BookStore.repository.ItemRepository;
import com.niit.BookStore.repository.PersonRepository;
import com.niit.BookStore.service.CartService;
import com.niit.BookStore.service.CustomConversionService;
import com.niit.BookStore.service.PromoService;
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
    private final CustomConversionService conversionService;
    private final PromoService promoService;

    private final PersonRepository personRepository;
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;


    @Autowired
    public CartServiceImpl(PersonRepository personRepository,
                           CartRepository cartRepository,
                           ItemRepository itemRepository,
                           CustomConversionService conversionService,
                           PromoService promoService) {
        this.personRepository = personRepository;
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.conversionService = conversionService;
        this.promoService = promoService;
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

    public Cart getPersonCart(Long person_id){
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
        clearCart(cart);
    }

    public void clearCart(Cart cart){
        cart.getItems().clear();
        cart.setAppliedBonuses(0);
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

    @Override
    public CartDto addPromoToCart(Long person_id, String promoCode){
        Cart cart = getPersonCart(person_id);
        Promo promo = promoService.getActivePromoByCode(promoCode);
        if(promoService.isApplicableForCart(promo, cart)){
            cart.setPromo(promo);
        }else {
            throw new AddPromoToCartException(
                    String.format("Promo code '%s' cannot be applied to your cart", promoCode));
        }
        return conversionService.convert(cart, CartDto.class);
    }

    @Override
    public CartDto deletePromoFromCart(Long person_id){
        Cart cart = getPersonCart(person_id);
        cart.setPromo(null);
        return conversionService.convert(cart, CartDto.class);
    }

    @Override
    public CartBonusDto applyBonuses(Long person_id, CartBonusDto cartBonusDto){
        Cart cart = getPersonCart(person_id);
        if(Objects.isNull(cartBonusDto.getBonuses()) || cartBonusDto.getBonuses() < 0){
            throw new AddBonusesToCartException("Wrong number of bonuses were provided, please check your request");
        }
        Person person = cart.getPerson();
        if (person.getBonus()<cartBonusDto.getBonuses()){
            throw new AddBonusesToCartException(
                    String.format("Wrong number of bonuses were provided. You cannot use more than %s",person.getBonus()));
        }
        cart.setAppliedBonuses(cartBonusDto.getBonuses());
        return new CartBonusDto(cartBonusDto.getBonuses());
    }

    @Override
    public void clearBonuses(Long person_id){
        Cart cart = getPersonCart(person_id);
        cart.setAppliedBonuses(0);
    }

    public CartBonusDto getAppliedBonuses(Long person_id){
        Cart cart = getPersonCart(person_id);
        return new CartBonusDto(cart.getAppliedBonuses());
    }
}
