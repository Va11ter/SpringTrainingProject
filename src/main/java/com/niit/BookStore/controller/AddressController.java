package com.niit.BookStore.controller;

import com.niit.BookStore.dto.AddressDto;
import com.niit.BookStore.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @GetMapping(value = "/{id}")
    public AddressDto getCartById(@PathVariable("id") Long id){
        return addressService.getAddressById(id);
    }

    @PostMapping
    public AddressDto createCart(@RequestBody AddressDto addressDto){
        return addressService.createAddress(addressDto);
    }

    @PutMapping(value = "/{id}")
    public AddressDto updateCart(@PathVariable("id") Long id, @RequestBody AddressDto addressDto){
        return addressService.updateAddress(id, addressDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCart(@PathVariable Long id){
        addressService.deleteAddress(id);
    }

    @GetMapping
    public List<AddressDto> getAll(){
        return addressService.getAll();
    }

}
