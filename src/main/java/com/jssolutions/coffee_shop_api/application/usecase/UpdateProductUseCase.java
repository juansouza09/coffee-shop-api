package com.jssolutions.coffee_shop_api.application.usecase;

import com.jssolutions.coffee_shop_api.application.dto.ProductRequest;
import com.jssolutions.coffee_shop_api.domain.Product;
import com.jssolutions.coffee_shop_api.infrastructure.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class UpdateProductUseCase {
    private final ProductRepository productRepository;

    public UpdateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(Long id, ProductRequest productRequest) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Product not found with id: " + id);
        }

        Product product = optionalProduct.get();

        if (productRequest.getName() != null) {
            product.setName(productRequest.getName());
        }

        if (productRequest.getPrice() != null) {
            product.setPrice(productRequest.getPrice());
        }

        return productRepository.save(product);
    }
}

