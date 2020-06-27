package com.niit.BookStore.converter;

import com.niit.BookStore.dto.ItemDto;
import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.dto.OrderItemDto;
import com.niit.BookStore.entiny.Item;
import com.niit.BookStore.entiny.Order;
import com.niit.BookStore.entiny.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class OrderItemToOrderItemDtoConverter implements Converter<OrderItem, OrderItemDto> {
    private final ConversionService conversionService;

    @Autowired
    public OrderItemToOrderItemDtoConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public OrderItemDto convert(OrderItem source) {
        return OrderItemDto.builder()
                .count(source.getCount())
                .price(source.getPrice())
                .itemDto(conversionService.convert(source.getItem(), ItemDto.class))
                .build();
    }
}
