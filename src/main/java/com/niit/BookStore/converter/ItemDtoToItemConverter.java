package com.niit.BookStore.converter;

import com.niit.BookStore.dto.ItemDto;
import com.niit.BookStore.entiny.Item;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ItemDtoToItemConverter implements Converter<ItemDto, Item> {
    @Override
    public Item convert(ItemDto source) {
        return Item.builder()
                .name(source.getName())
                .price(source.getPrice())
                .count(source.getCount())
                .description(source.getDescription())
                .build();
    }
}
