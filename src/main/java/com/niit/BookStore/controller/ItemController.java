package com.niit.BookStore.controller;

import com.niit.BookStore.dto.CartDto;
import com.niit.BookStore.dto.ItemDto;
import com.niit.BookStore.service.CartService;
import com.niit.BookStore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController extends BaseController{
    private ItemService itemService;
    private CartService cartService;

    @Autowired
    public ItemController(ItemService itemService, CartService cartService) {
        this.itemService = itemService;
        this.cartService = cartService;
    }

    @GetMapping(value = "/{id}")
    public ItemDto getItemById(@PathVariable("id") Long id){
        return itemService.getItemById(id);
    }

    @PostMapping
    public ItemDto createItem(@RequestBody ItemDto itemDto){
        return itemService.createItem(itemDto);
    }

    @PutMapping(value = "/{id}")
    public ItemDto updateItem(@PathVariable("id") Long id, @RequestBody ItemDto itemDto){
        return itemService.updateItem(id, itemDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);
    }

    @GetMapping
    public List<ItemDto> getAll(){
        return itemService.getAll();
    }

    @PostMapping(path = "/{item_id}/to_cart")
    public CartDto addItemToCart(@PathVariable("item_id") Long item_id){
        return cartService.addItem(getUserIdFromSecurityContext(), item_id);
    }
}
