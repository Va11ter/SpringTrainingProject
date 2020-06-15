package com.niit.BookStore.service.impl;

import com.niit.BookStore.dto.ItemDto;
import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.entiny.Item;
import com.niit.BookStore.entiny.Order;
import com.niit.BookStore.exception.ItemNotFoundException;
import com.niit.BookStore.repository.ItemRepository;
import com.niit.BookStore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "Item not found";
    private ItemRepository itemRepository;
    private ConversionService conversionService;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ConversionService conversionService) {
        this.itemRepository = itemRepository;
        this.conversionService = conversionService;
    }


    @Override
    public ItemDto getItemById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("NOT_FOUND_EXCEPTION_MESSAGE"));
        return conversionService.convert(item, ItemDto.class);
    }

    @Override
    public ItemDto createItem(ItemDto itemDto) {
        Item item = conversionService.convert(itemDto, Item.class);
        itemRepository.save(item);
        return conversionService.convert(item, ItemDto.class);
    }

    @Override
    public ItemDto updateItem(Long id, ItemDto itemDto) {
        Item item = itemRepository.findById(itemDto.getId()).orElseThrow(
                ()-> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        item.setCount(itemDto.getCount());
        item.setPrice(itemDto.getPrice());
        item.setDescription(itemDto.getDescription());
        item.setName(itemDto.getName());
        return conversionService.convert(item, ItemDto.class);
    }

    @Override
    public void deleteItem(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
        }else{
            throw new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public List<ItemDto> getAll() {
        List<Item> items = itemRepository.findAll();
        return items
                .stream()
                .map((Item item) -> conversionService.convert(item, ItemDto.class))
                .collect(Collectors.toList());
    }


}
