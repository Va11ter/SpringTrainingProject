package com.niit.BookStore.converter;

import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.dto.OrderItemDto;
import com.niit.BookStore.entiny.Order;
import com.niit.BookStore.entiny.OrderItem;
import com.niit.BookStore.entiny.Person;
import com.niit.BookStore.service.CustomConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderDtoToOrderConverter implements Converter<OrderDto, Order> {
    private final CustomConversionService conversionService;

    @Autowired
    public OrderDtoToOrderConverter(CustomConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Order convert(OrderDto source) {
        return Order.builder()
                .person(conversionService.convert(source.getPersonDto(), Person.class))
                .orderItems(conversionService.convert(source.getOrderItemsDto(), OrderItem.class))
                .build();
    }
}
