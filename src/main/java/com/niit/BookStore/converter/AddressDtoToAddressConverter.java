package com.niit.BookStore.converter;

import com.niit.BookStore.dto.AddressDto;
import com.niit.BookStore.entiny.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoToAddressConverter implements Converter<AddressDto, Address> {
    @Override
    public Address convert(AddressDto source) {
        Address target = new Address();
        target.setAdditionalInfo(source.getAdditionalInfo());
        target.setPostalCode(source.getPostalCode());
        target.setCountry(source.getCountry());
        target.setCity(source.getCity());
        target.setStreet(source.getStreet());
        target.setBuilding(source.getBuilding());
        target.setApartment(source.getApartment());
        return target;
    }
}
