package com.niit.BookStore.service.impl;

import com.niit.BookStore.dto.PromoDto;
import com.niit.BookStore.entiny.Cart;
import com.niit.BookStore.entiny.Category;
import com.niit.BookStore.entiny.Item;
import com.niit.BookStore.entiny.Person;
import com.niit.BookStore.entiny.Promo;
import com.niit.BookStore.entiny.enums.PromoType;
import com.niit.BookStore.exception.ItemNotFoundException;
import com.niit.BookStore.repository.PromoRepository;
import com.niit.BookStore.service.CustomConversionService;
import com.niit.BookStore.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Service
@Transactional
public class PromoServiceImpl implements PromoService {
    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "Promo not found";
    private final PromoRepository promoRepository;
    private final CustomConversionService conversionService;

    @Autowired
    public PromoServiceImpl(PromoRepository promoRepository, CustomConversionService conversionService) {
        this.promoRepository = promoRepository;
        this.conversionService = conversionService;
    }

    @Override
    public PromoDto getPromoById(Long id) {
        Promo promo = promoRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        return conversionService.convert(promo, PromoDto.class);
    }

    @Override
    public Promo getActivePromoByCode(String code){
        Promo promo = promoRepository.findPromoByCodeAndIsActiveIsTrue(code);
        if(Objects.isNull(promo) || !promo.isActive()){
            throw new ItemNotFoundException(String.format("Sorry, '%s' isn't found or not active anymore.", code));
        }
        return promo;
    }

    @Override
    public PromoDto createPromo(PromoDto promoDto) {
        Promo promo = conversionService.convert(promoDto, Promo.class);
        promoRepository.save(promo);
        return conversionService.convert(promo, PromoDto.class);
    }

    @Override
    public PromoDto updatePromo(Long id, PromoDto promoDto) {
        Promo promo = promoRepository.findById(promoDto.getId()).orElseThrow(
                ()-> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        promo.setStartDate(promoDto.getStartDate());
        promo.setStartDate(promo.getStartDate());
        promo.setEndDate(promoDto.getStartDate());
        promo.setCode(promoDto.getCode());
        promo.setActive(promoDto.isActive());
        return conversionService.convert(promo, PromoDto.class);
    }

    @Override
    public boolean isPromoActive(Long promoId) {
        Promo promo = promoRepository.findById(promoId).orElseThrow(
                ()-> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        return isPromoActive(promo);
    }

    @Override
    public boolean isPromoActive(Promo promo) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return promo.isActive()
                && promo.getStartDate().isBefore(localDateTime)
                && promo.getEndDate().isAfter(localDateTime);
    }

    @Override
    public void deletePromo(Long id) {
        if (promoRepository.existsById(id)) {
            promoRepository.deleteById(id);
        }else{
            throw new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public List<PromoDto> getAll() {
        List<Promo> promos = promoRepository.findAll();
        return promos
                .stream()
                .map((Promo promo) -> conversionService.convert(promo, PromoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isApplicableForCart(Promo promo, Cart cart) {
        switch (promo.getType()){
            case COMMON:
                return true;
            case PERSONAL:
                return isApplicableForPerson(promo, cart.getPerson());
            case PRODUCT_TYPE:
                for (Item item: cart.getItems()){
                    if(isApplicableForItem(promo, item)){
                        return true;
                    }
                }
        }
        return false;
    }

    @Override
    public boolean isApplicableForPerson(Promo promo, Person person) {
        return promo.getType() == PromoType.PERSONAL && promo.getPerson().equals(person);
    }

    @Override
    public boolean isApplicableForItem(Promo promo, Item item) {
        BiPredicate<Set<Category>, Set<Category>> haveCommonCategory;
        haveCommonCategory = (itemCategories, promoCategories)->{
            for (Category category: itemCategories){
                if(promoCategories.contains(category)){
                    return true;
                }
            }
            return false;
        };

        switch (promo.getType()){
            case COMMON:
                return true;
            case PERSONAL:
                return Objects.isNull(promo.getCategories())
                        || haveCommonCategory.test(item.getCategories(), promo.getCategories());
            case PRODUCT_TYPE:
                return !Objects.isNull(promo.getCategories())
                        && !Objects.isNull(item.getCategories())
                        && haveCommonCategory.test(item.getCategories(), promo.getCategories());
            default:
                return false;
        }
    }


}
