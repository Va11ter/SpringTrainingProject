package com.niit.BookStore.controller;

import com.niit.BookStore.dto.ItemDto;
import com.niit.BookStore.dto.PersonDto;
import com.niit.BookStore.repository.ItemRepository;
import com.niit.BookStore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
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

}
