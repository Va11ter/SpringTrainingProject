package com.niit.BookStore.converter;

import com.niit.BookStore.dto.AddressDto;
import com.niit.BookStore.entiny.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressToAddressDtoConverter implements Converter<Address, AddressDto> {
    @Override
    public AddressDto convert(Address source) {
        return AddressDto.builder()
                .id(source.getId())
                .additionalInfo(source.getAdditionalInfo())
                .postalCode(source.getPostalCode())
                .country(source.getCountry())
                .city(source.getCity())
                .street(source.getStreet())
                .building(source.getBuilding())
                .apartment(source.getApartment()).build();
    }
}
