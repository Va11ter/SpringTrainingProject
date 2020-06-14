package com.niit.BookStore.converter;

import com.niit.BookStore.dto.ItemDto;
import com.niit.BookStore.entiny.Item;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ItemToItemDtoConverter implements Converter<Item, ItemDto>{
    @Override
    public ItemDto convert(Item source) {
        ItemDto target = new ItemDto();
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setName(source.getName());
        target.setPrice(source.getPrice());
        target.setCount(source.getCount());
        return target;
    }
}