package com.niit.BookStore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.niit.BookStore.entiny.Promo;
import com.niit.BookStore.entiny.enums.PromoType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromoDto implements Serializable{
    private Long id;
    private String code;
    private String description;
    @JsonProperty("start_date")
    private LocalDateTime startDate;
    @JsonProperty("end_date")
    private LocalDateTime endDate;
    @JsonProperty("is_active")
    private boolean isActive;
    private Integer discount;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private PromoType type;
}