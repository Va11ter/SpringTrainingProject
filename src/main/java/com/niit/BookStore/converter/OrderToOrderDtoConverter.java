package com.niit.BookStore.converter;

import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.entiny.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderToOrderDtoConverter implements Converter<Order, OrderDto> {
    @Override
    public OrderDto convert(Order source) {
        OrderDto target = new OrderDto();
        target.setPersonId(source.getPersonId());
        target.setItems(source.getItems());
        target.setId(source.getId());
        return target;
    }
}
