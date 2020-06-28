package com.niit.BookStore.converter;

import com.niit.BookStore.dto.CategoryDto;
import com.niit.BookStore.entiny.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryDtoConverter implements Converter <Category, CategoryDto> {
    @Override
    public CategoryDto convert(Category source) {
        return CategoryDto.builder()
                .id(source.getId())
                .name(source.getName())
                .build();
    }
}
