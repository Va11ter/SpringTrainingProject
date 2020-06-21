package com.niit.BookStore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto implements Serializable {
    private Long id;
    @JsonProperty("postal_code")
    private String postalCode;
    private String country;
    private String city;
    private String street;
    private String building;
    private String apartment;
    @JsonProperty("additional_info")
    private String additionalInfo;
}
