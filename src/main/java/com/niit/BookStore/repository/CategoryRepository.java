package com.niit.BookStore.repository;

import com.niit.BookStore.entiny.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{
}
