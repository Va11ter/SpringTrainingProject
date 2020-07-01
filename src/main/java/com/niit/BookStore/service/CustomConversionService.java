package com.niit.BookStore.service;

import org.springframework.core.convert.support.ConfigurableConversionService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public interface CustomConversionService extends ConfigurableConversionService {
    default <T, S> List<T> convert(List<S> source, Class<T> targetClass){
        return Objects.isNull(source) ? Collections.emptyList():
            source.stream()
                    .map((S src)-> convert(src, targetClass))
                    .collect(Collectors.toList());
    };

    default <T, S> Set<T> convert(Set<S> source, Class<T> targetClass){
        return Objects.isNull(source) ? Collections.emptySet():
                source.stream()
                        .map((S src)-> convert(src, targetClass))
                        .collect(Collectors.toSet());
    };
}
