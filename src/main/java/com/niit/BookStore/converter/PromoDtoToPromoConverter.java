package com.niit.BookStore.converter;

import com.niit.BookStore.dto.PromoDto;
import com.niit.BookStore.entiny.Promo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PromoDtoToPromoConverter implements Converter<PromoDto, Promo> {
    @Override
    public Promo convert(PromoDto source) {
        return Promo.builder()
                .code(source.getCode())
                .description(source.getDescription())
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .discount(source.getDiscount())
                .isActive(source.isActive())
                .type(source.getType())
                .build();
    }
}
