package com.niit.BookStore.converter;

import com.niit.BookStore.dto.PromoDto;
import com.niit.BookStore.entiny.Promo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PromoToPromoDtoConverter implements Converter<Promo, PromoDto> {
    @Override
    public PromoDto convert(Promo source) {
        return PromoDto.builder()
                .id(source.getId())
                .code(source.getCode())
                .description(source.getDescription())
                .endDate(source.getEndDate())
                .startDate(source.getStartDate())
                .isActive(source.isActive())
                .discount(source.getDiscount()).build();
    }
}
