package com.jssolutions.coffee_shop_api.infrastructure.controllers;

import com.jssolutions.coffee_shop_api.domain.Address;
import com.jssolutions.coffee_shop_api.infrastructure.services.CepServiceImpl;
import com.jssolutions.coffee_shop_api.infrastructure.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final LogService logService;
    private final CepServiceImpl cepService;

    @Autowired
    public AddressController(LogService logService, CepServiceImpl cepService) {
        this.logService = logService;
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public Address getAddressByCep(@PathVariable String cep) {
        logService.logApiCall("Request: GET /api/address/" + cep, null);

        Address address = cepService.getAddressByCep(cep);

        logService.logApiCall(null, "Response: " + (address != null ? address.toString() : "Address not found"));

        return address;
    }
}
