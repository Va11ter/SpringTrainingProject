package com.niit.BookStore.converter;

import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.dto.OrderItemDto;
import com.niit.BookStore.dto.PersonDto;
import com.niit.BookStore.entiny.Order;
import com.niit.BookStore.service.CustomConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderToOrderDtoConverter implements Converter<Order, OrderDto> {
    private CustomConversionService conversionService;

    @Autowired
    public OrderToOrderDtoConverter(CustomConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public OrderDto convert(Order source) {
        return OrderDto.builder()
                .id(source.getId())
                .personDto(conversionService.convert(source.getPerson(), PersonDto.class))
                .orderItemsDto(conversionService.convert(source.getOrderItems(), OrderItemDto.class))
                .state(source.getState().toString())
                .total(source.getTotal())
                .build();
    }
}
