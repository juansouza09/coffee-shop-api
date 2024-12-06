package com.jssolutions.coffee_shop_api.application.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private Double price;
}
