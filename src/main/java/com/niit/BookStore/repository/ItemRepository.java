package com.niit.BookStore.repository;

import com.niit.BookStore.entiny.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
