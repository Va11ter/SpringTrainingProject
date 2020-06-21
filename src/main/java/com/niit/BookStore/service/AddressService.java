package com.niit.BookStore.service;


import com.niit.BookStore.dto.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto getAddressById(Long id);
    AddressDto createAddress(AddressDto addressDto);
    AddressDto updateAddress(Long id, AddressDto addressDto);
    void deleteAddress(Long id);
    List<AddressDto> getAll();
}
