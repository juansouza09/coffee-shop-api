package com.jssolutions.coffee_shop_api.application.dto;

import com.jssolutions.coffee_shop_api.domain.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaleResponse {
    private Long id;
    private String userName;
    private String contactNumber;
    private Long productId;
    private Double total;
    private Address address;
}
