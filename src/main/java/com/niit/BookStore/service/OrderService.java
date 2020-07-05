package com.niit.BookStore.service;

import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.entiny.Cart;
import com.niit.BookStore.entiny.Order;

import java.util.List;

public interface OrderService {
    OrderDto getOrderById(Long id);
    OrderDto cancelOrder(Long id);
    OrderDto completeOrder(Long id);
    OrderDto makeOrder(Cart cart);
    List<OrderDto> getAll();
}
