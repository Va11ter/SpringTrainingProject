package com.niit.BookStore.service.impl;

import com.niit.BookStore.dto.PromoDto;
import com.niit.BookStore.entiny.Promo;
import com.niit.BookStore.exception.ItemNotFoundException;
import com.niit.BookStore.repository.PromoRepository;
import com.niit.BookStore.service.CustomConversionService;
import com.niit.BookStore.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PromoServiceImpl implements PromoService {
    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "Promo not found";
    private PromoRepository promoRepository;
    private CustomConversionService conversionService;

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
    public boolean isPromoActive(Long id) {
        Promo promo = promoRepository.findById(id).orElseThrow(
                ()-> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
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
        List<Promo> promoes = promoRepository.findAll();
        return promoes
                .stream()
                .map((Promo promo) -> conversionService.convert(promo, PromoDto.class))
                .collect(Collectors.toList());
    }
}
