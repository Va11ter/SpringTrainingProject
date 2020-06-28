package com.niit.BookStore.service.impl;

import com.niit.BookStore.dto.CategoryDto;
import com.niit.BookStore.dto.ItemDto;
import com.niit.BookStore.entiny.Category;
import com.niit.BookStore.entiny.Item;
import com.niit.BookStore.exception.ItemNotFoundException;
import com.niit.BookStore.repository.CategoryRepository;
import com.niit.BookStore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "Category not found";
    private CategoryRepository categoryRepository;
    private ConversionService conversionService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ConversionService conversionService) {
        this.categoryRepository = categoryRepository;
        this.conversionService = conversionService;
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        return conversionService.convert(category, CategoryDto.class);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = conversionService.convert(categoryDto, Category.class);
        categoryRepository.save(category);
        return conversionService.convert(category, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        category.setName(categoryDto.getName());
        return conversionService.convert(category, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        }else{
            throw new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories
                .stream()
                .map((Category category) -> conversionService.convert(category, CategoryDto.class))
                .collect(Collectors.toList());
    }
}