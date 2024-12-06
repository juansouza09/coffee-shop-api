package com.jssolutions.coffee_shop_api.domain.services;

import com.jssolutions.coffee_shop_api.domain.Address;

public interface CepService {
    Address getAddressByCep(String cep);
}
