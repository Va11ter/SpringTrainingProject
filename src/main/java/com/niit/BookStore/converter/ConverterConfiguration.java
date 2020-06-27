package com.niit.BookStore.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ConverterConfiguration {
    private final Set<Converter<?,?>> converters;
    private final ConfigurableConversionService configurableConversionService;

    @Autowired
    public ConverterConfiguration(Set<Converter<?, ?>> converters,
                                  ConfigurableConversionService configurableConversionService) {
        this.converters = converters;
        this.configurableConversionService = configurableConversionService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onContextRefreshedEvent(){
        converters.forEach(configurableConversionService::addConverter);
    }

}

