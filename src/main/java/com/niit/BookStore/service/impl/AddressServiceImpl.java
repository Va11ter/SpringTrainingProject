package com.niit.BookStore.service.impl;

import com.niit.BookStore.dto.AddressDto;
import com.niit.BookStore.entiny.Address;
import com.niit.BookStore.exception.ItemNotFoundException;
import com.niit.BookStore.repository.AddressRepository;
import com.niit.BookStore.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "Address not found";
    private AddressRepository addressRepository;
    private ConversionService conversionService;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, ConversionService conversionService) {
        this.addressRepository = addressRepository;
        this.conversionService = conversionService;
    }

    @Override
    public AddressDto getAddressById(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        return conversionService.convert(address, AddressDto.class);
    }

    @Override
    public AddressDto createAddress(AddressDto addressDto) {
        Address address = conversionService.convert(addressDto, Address.class);
        addressRepository.save(address);
        return conversionService.convert(address, AddressDto.class);
    }

    @Override
    public AddressDto updateAddress(Long id, AddressDto addressDto) {
        Address address = addressRepository.findById(addressDto.getId()).orElseThrow(
                ()-> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        address.setAdditionalInfo(addressDto.getAdditionalInfo());
        address.setPostalCode(addressDto.getPostalCode());
        address.setCountry(addressDto.getCountry());
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setBuilding(addressDto.getBuilding());
        address.setApartment(addressDto.getApartment());
        return conversionService.convert(address, AddressDto.class);
    }

    @Override
    public void deleteAddress(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        }else{
            throw new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public List<AddressDto> getAll() {
        List<Address> addresses = addressRepository.findAll();
        return addresses
                .stream()
                .map((Address address) -> conversionService.convert(address, AddressDto.class))
                .collect(Collectors.toList());
    }
}
