package com.jssolutions.coffee_shop_api.application.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
