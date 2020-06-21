package com.niit.BookStore.converter;

import com.niit.BookStore.dto.PromoDto;
import com.niit.BookStore.entiny.Promo;
import org.springframework.core.convert.converter.Converter;

public class PromoDtoToPromoConverter implements Converter<PromoDto, Promo> {
    @Override
    public Promo convert(PromoDto source) {
        Promo promo = new Promo();
        promo.setActive(source.isActive());
        promo.setCode(source.getCode());
        promo.setDescription(source.getDescription());
        promo.setEndDate(source.getEndDate());
        promo.setStartDate(source.getStartDate());
        promo.setDiscount(source.getDiscount());
        return promo;
    }
}
