package com.jssolutions.coffee_shop_api.application.usecase;

import com.jssolutions.coffee_shop_api.infrastructure.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductUseCase {
    private final ProductRepository productRepository;

    public DeleteProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }

        productRepository.deleteById(id);
    }
}
