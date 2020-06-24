package com.niit.BookStore.converter;

import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.entiny.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoToOrderConverter implements Converter<OrderDto, Order> {
    @Override
    public Order convert(OrderDto source) {
        return Order.builder()
                .person(source.getPerson())
                .items(source.getItems())
                .build();
    }
}
