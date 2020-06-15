package com.niit.BookStore.repository;

import com.niit.BookStore.entiny.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
