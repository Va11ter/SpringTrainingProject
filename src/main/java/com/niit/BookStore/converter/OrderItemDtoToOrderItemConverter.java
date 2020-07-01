package com.niit.BookStore.converter;

import com.niit.BookStore.dto.OrderItemDto;
import com.niit.BookStore.entiny.Item;
import com.niit.BookStore.entiny.OrderItem;
import com.niit.BookStore.service.CustomConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderItemDtoToOrderItemConverter implements Converter<OrderItemDto, OrderItem> {
    private final CustomConversionService conversionService;

    @Autowired
    public OrderItemDtoToOrderItemConverter(CustomConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public OrderItem convert(OrderItemDto source) {
        return OrderItem.builder()
                .count(source.getCount())
                .price(source.getPrice())
                .item(conversionService.convert(source.getItemDto(), Item.class))
                .build();
    }
}
