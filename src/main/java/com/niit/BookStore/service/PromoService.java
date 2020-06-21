package com.niit.BookStore.service;

import com.niit.BookStore.dto.PromoDto;

import java.util.List;

public interface PromoService {
    PromoDto getPromoById(Long id);
    PromoDto createPromo(PromoDto promoDto);
    PromoDto updatePromo(Long id, PromoDto promoDto);
    boolean isPromoActive(Long id);
    void deletePromo(Long id);
    List<PromoDto> getAll();
}
