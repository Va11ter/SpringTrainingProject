package com.niit.BookStore.service.impl;

import com.niit.BookStore.dto.WishListDto;
import com.niit.BookStore.entiny.WishList;
import com.niit.BookStore.exception.ItemNotFoundException;
import com.niit.BookStore.repository.WishListRepository;
import com.niit.BookStore.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WishListServiceImpl implements WishListService {
    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "WishList not found";
    private WishListRepository wishListRepository;
    private ConversionService conversionService;

    @Autowired
    public WishListServiceImpl(WishListRepository wishListRepository, ConversionService conversionService) {
        this.wishListRepository = wishListRepository;
        this.conversionService = conversionService;
    }

    @Override
    public WishListDto getWishListById(Long id) {
        WishList wishList = wishListRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("NOT_FOUND_EXCEPTION_MESSAGE"));
        return conversionService.convert(wishList, WishListDto.class);
    }

    @Override
    public WishListDto createWishList(WishListDto wishListDto) {
        WishList wishList = conversionService.convert(wishListDto, WishList.class);
        wishListRepository.save(wishList);
        return conversionService.convert(wishList, WishListDto.class);
    }

    @Override
    public WishListDto updateWishList(Long id, WishListDto wishListDto) {
        WishList wishList = wishListRepository.findById(wishListDto.getId()).orElseThrow(
                ()-> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        wishList.setItems(wishListDto.getItems());
        wishList.setPersonId(wishListDto.getPersonId());
        return conversionService.convert(wishList, WishListDto.class);
    }

    @Override
    public void deleteWishList(Long id) {
        if (wishListRepository.existsById(id)) {
            wishListRepository.deleteById(id);
        }else{
            throw new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public List<WishListDto> getAll() {
        List<WishList> wishLists = wishListRepository.findAll();
        return wishLists
                .stream()
                .map((WishList wishList) -> conversionService.convert(wishList, WishListDto.class))
                .collect(Collectors.toList());
    }

}
