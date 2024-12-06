package com.jssolutions.coffee_shop_api.infrastructure.controllers;

import com.jssolutions.coffee_shop_api.domain.Address;
import com.jssolutions.coffee_shop_api.infrastructure.services.CepServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private CepServiceImpl cepService;

    @GetMapping("/{cep}")
    public Address getAddressByCep(@PathVariable String cep) {
        return cepService.getAddressByCep(cep);
    }
}
