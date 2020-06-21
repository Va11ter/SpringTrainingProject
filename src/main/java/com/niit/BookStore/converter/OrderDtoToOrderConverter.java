package com.niit.BookStore.converter;

import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.entiny.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoToOrderConverter implements Converter<OrderDto, Order> {
    @Override
    public Order convert(OrderDto source) {
        Order target = new Order();
        target.setPerson(source.getPerson());
        target.setItems(source.getItems());
        return target;
    }
}
