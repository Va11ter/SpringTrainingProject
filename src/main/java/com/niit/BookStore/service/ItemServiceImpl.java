package com.niit.BookStore.service;

import com.niit.BookStore.dto.ItemDto;
import com.niit.BookStore.entiny.Item;
import com.niit.BookStore.exception.ItemNotFoundException;
import com.niit.BookStore.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
        return null;
    }

    @Override
    public ItemDto updateItem(Long id, ItemDto itemDto) {
        return null;
    }

    @Override
    public void deleteItem(Long id) {

    }

    @Override
    public List<ItemDto> getAll() {
        return null;
    }
}
