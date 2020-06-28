package com.niit.BookStore.converter;

import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.dto.OrderItemDto;
import com.niit.BookStore.dto.PersonDto;
import com.niit.BookStore.entiny.Order;
import com.niit.BookStore.entiny.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderToOrderDtoConverter implements Converter<Order, OrderDto> {
    private ConversionService conversionService;

    @Autowired
    public OrderToOrderDtoConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public OrderDto convert(Order source) {
        return OrderDto.builder()
                .id(source.getId())
                .personDto(conversionService.convert(source.getPerson(), PersonDto.class))
                .orderItemsDto((source.getOrderItems()
                        .stream()
                        .map((OrderItem orderItem)-> conversionService.convert(source.getPerson(), OrderItemDto.class))
                        .collect(Collectors.toSet())))
                .build();
    }
}
