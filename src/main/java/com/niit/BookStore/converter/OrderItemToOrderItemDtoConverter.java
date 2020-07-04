package com.niit.BookStore.converter;

import com.niit.BookStore.dto.ItemDto;
import com.niit.BookStore.dto.OrderItemDto;
import com.niit.BookStore.entiny.OrderItem;
import com.niit.BookStore.service.CustomConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class OrderItemToOrderItemDtoConverter implements Converter<OrderItem, OrderItemDto> {
    private final CustomConversionService conversionService;

    @Autowired
    public OrderItemToOrderItemDtoConverter(CustomConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public OrderItemDto convert(OrderItem source) {
        return OrderItemDto.builder()
                .id(source.getId())
                .count(source.getCount())
                .price(source.getPrice())
                .itemDto(conversionService.convert(source.getItem(), ItemDto.class))
                .build();
    }
}
