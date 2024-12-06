package com.jssolutions.coffee_shop_api.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
}
