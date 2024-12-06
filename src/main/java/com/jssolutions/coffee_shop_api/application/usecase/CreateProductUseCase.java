package com.jssolutions.coffee_shop_api.application.usecase;

import com.jssolutions.coffee_shop_api.application.dto.ProductRequest;
import com.jssolutions.coffee_shop_api.domain.Product;
import com.jssolutions.coffee_shop_api.infrastructure.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUseCase {
    private final ProductRepository productRepository;

    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());

        return productRepository.save(product);
    }
}
