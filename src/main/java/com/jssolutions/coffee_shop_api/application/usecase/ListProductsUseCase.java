package com.jssolutions.coffee_shop_api.application.usecase;

import com.jssolutions.coffee_shop_api.domain.Product;
import com.jssolutions.coffee_shop_api.infrastructure.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListProductsUseCase {
    private final ProductRepository productRepository;

    public ListProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> execute() {
        return productRepository.findAll();
    }
}
