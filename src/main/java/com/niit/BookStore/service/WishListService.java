package com.niit.BookStore.service;

import com.niit.BookStore.dto.WishListDto;

import java.util.List;

public interface WishListService {
    WishListDto getWishListById(Long id);
    WishListDto createWishList(WishListDto wishListDto);
    WishListDto updateWishList(Long id, WishListDto wishListDto);
    void deleteWishList(Long id);
    List<WishListDto> getAll();
}
