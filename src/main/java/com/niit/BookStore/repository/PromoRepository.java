package com.niit.BookStore.repository;

import com.niit.BookStore.entiny.Promo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoRepository extends JpaRepository<Promo, Long> {
    Promo findPromoByCodeAndIsActiveIsTrue(String code);
}
