package com.niit.BookStore.converter;

import com.niit.BookStore.service.CustomConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ConverterConfiguration {
    private final Set<Converter<?,?>> converters;
    private final CustomConversionService customConversionService;

    @Autowired
    public ConverterConfiguration(Set<Converter<?, ?>> converters,
                                  CustomConversionService customConversionService) {
        this.converters = converters;
        this.customConversionService = customConversionService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onContextRefreshedEvent(){
        converters.forEach(customConversionService::addConverter);
    }

}

