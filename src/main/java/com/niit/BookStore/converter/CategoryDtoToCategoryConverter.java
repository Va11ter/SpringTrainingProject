package com.niit.BookStore.converter;

import com.niit.BookStore.dto.CategoryDto;
import com.niit.BookStore.entiny.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoToCategoryConverter implements Converter<CategoryDto, Category> {
    @Override
    public Category convert(CategoryDto source) {
        return Category.builder()
                .name(source.getName())
                .build();
    }
}
