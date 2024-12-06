package com.jssolutions.coffee_shop_api.application.usecase;

import com.jssolutions.coffee_shop_api.application.dto.SaleRequest;
import com.jssolutions.coffee_shop_api.domain.Address;
import com.jssolutions.coffee_shop_api.domain.Sale;
import com.jssolutions.coffee_shop_api.infrastructure.repositories.SaleRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateSaleUseCase {
    private final SaleRepository saleRepository;

    public CreateSaleUseCase(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Sale execute(SaleRequest saleRequest, Address address) {
        Sale sale = new Sale(
                null,
                saleRequest.getUserName(),
                saleRequest.getContactNumber(),
                saleRequest.getProductId(),
                saleRequest.getTotal(),
                address
        );

        return saleRepository.save(sale);
    }

}
