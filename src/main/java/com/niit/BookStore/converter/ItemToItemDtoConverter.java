package com.niit.BookStore.converter;

import com.niit.BookStore.dto.ItemDto;
import com.niit.BookStore.entiny.Item;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ItemToItemDtoConverter implements Converter<Item, ItemDto>{
    @Override
    public ItemDto convert(Item source) {
        return ItemDto.builder()
                .id(source.getId())
                .description(source.getDescription())
                .name(source.getName())
                .price(source.getPrice())
                .count(source.getCount())
                .build();
    }
}