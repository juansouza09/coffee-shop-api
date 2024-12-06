package com.jssolutions.coffee_shop_api.application.usecase;

import com.jssolutions.coffee_shop_api.domain.Sale;
import com.jssolutions.coffee_shop_api.infrastructure.repositories.ProductRepository;
import com.jssolutions.coffee_shop_api.infrastructure.repositories.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSalesUseCase {
    private final SaleRepository saleRepository;

    public ListSalesUseCase(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> execute() {
        return saleRepository.findAll();
    }
}
