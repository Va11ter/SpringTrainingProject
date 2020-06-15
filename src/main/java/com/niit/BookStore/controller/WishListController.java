package com.niit.BookStore.controller;

import com.niit.BookStore.dto.WishListDto;
import com.niit.BookStore.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishList")
public class WishListController {
    private final WishListService wishListService;

    @Autowired
    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }


    @GetMapping(value = "/{id}")
    public WishListDto getWishListById(@PathVariable("id") Long id){
        return wishListService.getWishListById(id);
    }

    @PostMapping
    public WishListDto createWishList(@RequestBody WishListDto wishListDto){
        return wishListService.createWishList(wishListDto);
    }

    @PutMapping(value = "/{id}")
    public WishListDto updateWishList(@PathVariable("id") Long id, @RequestBody WishListDto wishListDto){
        return wishListService.updateWishList(id, wishListDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteWishList(@PathVariable Long id){
        wishListService.deleteWishList(id);
    }

    @GetMapping
    public List<WishListDto> getAll(){
        return wishListService.getAll();
    }

}
