package com.niit.BookStore.service;

import com.niit.BookStore.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto getCategoryById(Long id);
    CategoryDto createCategory(CategoryDto itemDto);
    CategoryDto updateCategory(Long id, CategoryDto itemDto);
    void deleteCategory(Long id);
    List<CategoryDto> getAll();
}
