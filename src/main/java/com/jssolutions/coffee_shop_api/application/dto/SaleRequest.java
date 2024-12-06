package com.jssolutions.coffee_shop_api.application.dto;

import lombok.Data;

@Data
public class SaleRequest {
    private String userName;
    private String contactNumber;
    private Long productId;
    private Double total;
    private String cep;
}
