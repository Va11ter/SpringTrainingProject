package com.niit.BookStore.converter;

import com.niit.BookStore.dto.AddressDto;
import com.niit.BookStore.entiny.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoToAddressConverter implements Converter<AddressDto, Address> {
    @Override
    public Address convert(AddressDto source) {
        return Address.builder()
                .postalCode(source.getPostalCode())
                .country(source.getCountry())
                .city(source.getCity())
                .street(source.getStreet())
                .building(source.getBuilding())
                .apartment(source.getApartment())
                .additionalInfo(source.getAdditionalInfo())
                .build();
    }
}
