package com.niit.BookStore.converter;

import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.dto.OrderItemDto;
import com.niit.BookStore.entiny.Order;
import com.niit.BookStore.entiny.OrderItem;
import com.niit.BookStore.entiny.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderDtoToOrderConverter implements Converter<OrderDto, Order> {
    private final ConversionService conversionService;

    @Autowired
    public OrderDtoToOrderConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Order convert(OrderDto source) {
        return Order.builder()
                .person(conversionService.convert(source.getPersonDto(), Person.class))
                .orderItems(source.getOrderItemDto()
                        .stream()
                        .map((OrderItemDto orderItemDto) -> conversionService.convert(orderItemDto, OrderItem.class))
                        .collect(Collectors.toSet()))
                .build();
    }
}
