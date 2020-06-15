package com.niit.BookStore.converter;

import com.niit.BookStore.dto.WishListDto;
import com.niit.BookStore.entiny.WishList;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WishListDtoToWishListConverter implements Converter<WishListDto, WishList> {
    @Override
    public WishList convert(WishListDto source) {
        WishList target = new WishList();
        target.setPersonId(source.getPersonId());
        target.setItems(source.getItems());
        return target;
    }
}
