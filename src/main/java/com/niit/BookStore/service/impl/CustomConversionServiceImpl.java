package com.niit.BookStore.service.impl;

import com.niit.BookStore.service.CustomConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Service;

@Service
public class CustomConversionServiceImpl extends GenericConversionService implements CustomConversionService {
}
