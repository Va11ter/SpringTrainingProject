package com.niit.BookStore.service;

import com.niit.BookStore.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto getItemById(Long id);
    ItemDto createItem(ItemDto itemDto);
    ItemDto updateItem(Long id, ItemDto itemDto);
    void deleteItem(Long id);
    List<ItemDto> getAll();
}
