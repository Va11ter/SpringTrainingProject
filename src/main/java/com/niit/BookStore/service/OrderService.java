package com.niit.BookStore.service;

import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.entiny.Cart;

import java.util.List;

public interface OrderService {
    OrderDto getOrderById(Long id);
    OrderDto updateOrder(Long id, OrderDto orderDto);
    OrderDto makeOrder(Cart cart);
    List<OrderDto> getAll();
}
