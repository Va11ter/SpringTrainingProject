package com.niit.BookStore.controller;

import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/{id}")
    public OrderDto getOrderById(@PathVariable("id") Long id){
        return orderService.getOrderById(id);
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto){
        return orderService.createOrder(orderDto);
    }

    @PutMapping(value = "/{id}")
    public OrderDto updateOrder(@PathVariable("id") Long id, @RequestBody OrderDto orderDto){
        return orderService.updateOrder(id, orderDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
    }

    @GetMapping
    public List<OrderDto> getAll(){
        return orderService.getAll();
    }

}
