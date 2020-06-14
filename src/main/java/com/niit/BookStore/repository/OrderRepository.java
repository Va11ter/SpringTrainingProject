package com.niit.BookStore.repository;

import com.niit.BookStore.entiny.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
