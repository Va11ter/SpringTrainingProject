package com.niit.BookStore.service;

import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.entiny.Person;

import java.util.List;

public interface OrderService {
    OrderDto getOrderById(Long id);
    OrderDto createOrder(OrderDto itemDto);
    OrderDto updateOrder(Long id, OrderDto orderDto);
    void deleteOrder(Long id);
    List<OrderDto> getAll();
}
