package com.niit.BookStore.converter;

import com.niit.BookStore.dto.ItemDto;
import com.niit.BookStore.entiny.Item;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ItemDtoToItemConverter implements Converter<ItemDto, Item> {
    @Override
    public Item convert(ItemDto source) {
        Item target = new Item();
        target.setDescription(source.getDescription());
        target.setName(source.getName());
        target.setPrice(source.getPrice());
        target.setCount(source.getCount());
        return target;
    }
}
