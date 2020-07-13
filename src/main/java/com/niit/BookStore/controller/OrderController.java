package com.niit.BookStore.controller;

import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PutMapping(value = "/{id}/cancel")
    public OrderDto cancelOrder(@PathVariable("id") Long id){
        return orderService.cancelOrder(id);
    }

    @PutMapping(value = "/{id}/complete")
    @PreAuthorize(value =  "hasRole('ADMIN') || hasRole('SUPERVISOR')")
    public OrderDto completeOrder(@PathVariable("id") Long id){
        return orderService.completeOrder(id);
    }

    @GetMapping
    public List<OrderDto> getAll(){
        return orderService.getAll();
    }

}
