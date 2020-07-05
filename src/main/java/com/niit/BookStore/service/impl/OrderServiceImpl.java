package com.niit.BookStore.service.impl;

import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.entiny.Cart;
import com.niit.BookStore.entiny.Item;
import com.niit.BookStore.entiny.Order;
import com.niit.BookStore.entiny.OrderItem;
import com.niit.BookStore.entiny.Person;
import com.niit.BookStore.entiny.enums.OrderState;
import com.niit.BookStore.exception.ChangeStateOrderException;
import com.niit.BookStore.exception.ItemNotFoundException;
import com.niit.BookStore.exception.MakeOrderAppException;
import com.niit.BookStore.repository.OrderRepository;
import com.niit.BookStore.service.CustomConversionService;
import com.niit.BookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        Order order = orderRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        return conversionService.convert(order, OrderDto.class);
    }

    @Override
    public OrderDto cancelOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));

        if(order.getState() == OrderState.CANCELED || order.getState() == OrderState.COMPLETED){
            throw new ChangeStateOrderException(String.format("You cannot cancel order in state: '%s'",
                    order.getState()));
        }

        for (OrderItem orderItem: order.getOrderItems()){
            Item item = orderItem.getItem();
            item.setCount(item.getCount()+orderItem.getCount());
        }

        order.setState(OrderState.CANCELED);

        return conversionService.convert(order, OrderDto.class);
    }

    @Override
    public OrderDto completeOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));

        if(order.getState() == OrderState.CANCELED || order.getState() == OrderState.COMPLETED){
            throw new ChangeStateOrderException(String.format("You cannot complete order in state: '%s'",
                    order.getState()));
        }

        order.setState(OrderState.COMPLETED);

        return conversionService.convert(order, OrderDto.class);
    }



    @Override
    public OrderDto makeOrder(Cart cart) {
        Order newOrder = Order.builder()
                .person(cart.getPerson())
                .address(cart.getPerson().getAddress())
                .placedOn(LocalDateTime.now())
                .build();

        BigDecimal orderPrice = new BigDecimal(0);
        Set<OrderItem> orderItemSet = new HashSet<>();
        for (Item item : cart.getItems()) {
            OrderItem orderItem = OrderItem.builder()
                    .order(newOrder)
                    .item(item)
                    .count(1)
                    .price(item.getPrice())
                    .build();

            if(item.getCount()-1 < 0){
                throw new MakeOrderAppException(String.format("Sorry, there isn't enough '%s' items in stock", item.getName()));
            }
            item.setCount(item.getCount()-1);
            orderItem.setPrice(item.getPrice());
            orderPrice = orderPrice.add(orderItem.getPrice());
            orderItemSet.add(orderItem);
        }
        newOrder.setOrderItems(orderItemSet);

        newOrder.setTotal(orderPrice);
        orderRepository.save(newOrder);
        return conversionService.convert(newOrder, OrderDto.class);
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
