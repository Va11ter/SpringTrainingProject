package com.niit.BookStore.converter;

import com.niit.BookStore.dto.WishListDto;
import com.niit.BookStore.entiny.WishList;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WishListToWishListDtoConverter implements Converter<WishList, WishListDto> {
    @Override
    public WishListDto convert(WishList source) {
        WishListDto target = new WishListDto();
        target.setPersonId(source.getPersonId());
        target.setItems(source.getItems());
        target.setId(source.getId());
        return target;
    }
}
