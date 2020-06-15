package com.niit.BookStore.repository;

import com.niit.BookStore.entiny.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList, Long> {
}
