package com.niit.BookStore.controller;

import com.niit.BookStore.dto.PromoDto;
import com.niit.BookStore.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promos")
public class PromoController {
    private final PromoService promoService;

    @Autowired
    public PromoController(PromoService promoService) {this.promoService=promoService;}

    @GetMapping(value = "/{id}")
    public PromoDto getPromoById(@PathVariable("id") Long id){
        return promoService.getPromoById(id);
    }

    @PostMapping
    public PromoDto createPromo(@RequestBody PromoDto promoDto){
        return promoService.createPromo(promoDto);
    }

    @PutMapping(value = "/{id}")
    public PromoDto updatePromo(@PathVariable("id") Long id, @RequestBody PromoDto promoDto){
        return promoService.updatePromo(id, promoDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePromo(@PathVariable Long id){
        promoService.deletePromo(id);
    }

    @GetMapping
    public List<PromoDto> getAll(){
        return promoService.getAll();
    }

}
