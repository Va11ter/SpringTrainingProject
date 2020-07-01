package com.niit.BookStore.service.impl;

import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.entiny.Order;
import com.niit.BookStore.entiny.OrderItem;
import com.niit.BookStore.entiny.Person;
import com.niit.BookStore.exception.ItemNotFoundException;
import com.niit.BookStore.repository.OrderRepository;
import com.niit.BookStore.service.CustomConversionService;
import com.niit.BookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "Order not found";
    private OrderRepository orderRepository;
    private CustomConversionService conversionService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomConversionService conversionService) {
        this.orderRepository = orderRepository;
        this.conversionService = conversionService;
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("NOT_FOUND_EXCEPTION_MESSAGE"));
        return conversionService.convert(order, OrderDto.class);
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = conversionService.convert(orderDto, Order.class);
        orderRepository.save(order);
        return conversionService.convert(order, OrderDto.class);
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        Order order = orderRepository.findById(orderDto.getId()).orElseThrow(
                () -> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        order.setOrderItems(conversionService.convert(orderDto.getOrderItemsDto(), OrderItem.class));
        order.setPerson(conversionService.convert(orderDto.getPersonDto(), Person.class));
        return conversionService.convert(order, OrderDto.class);
    }

    @Override
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public List<OrderDto> getAll() {
        List<Order> orders = orderRepository.findAll();
        return orders
                .stream()
                .map((Order order) -> conversionService.convert(order, OrderDto.class))
                .collect(Collectors.toList());
    }

}
