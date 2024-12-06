package com.jssolutions.coffee_shop_api.infrastructure.controllers;

import com.jssolutions.coffee_shop_api.application.dto.ProductRequest;
import com.jssolutions.coffee_shop_api.application.dto.ProductResponse;
import com.jssolutions.coffee_shop_api.application.usecase.CreateProductUseCase;
import com.jssolutions.coffee_shop_api.application.usecase.DeleteProductUseCase;
import com.jssolutions.coffee_shop_api.application.usecase.ListProductsUseCase;
import com.jssolutions.coffee_shop_api.application.usecase.UpdateProductUseCase;
import com.jssolutions.coffee_shop_api.domain.Product;
import com.jssolutions.coffee_shop_api.infrastructure.log.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ListProductsUseCase listProductsUseCase;
    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final LogService logService;

    public ProductController(ListProductsUseCase listProductsUseCase, CreateProductUseCase createProductUseCase,
                             UpdateProductUseCase updateProductUseCase, DeleteProductUseCase deleteProductUseCase,
                             LogService logService) {
        this.listProductsUseCase = listProductsUseCase;
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.logService = logService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        logService.logApiCall("Request: GET /api/products", null);

        List<ProductResponse> products = listProductsUseCase.execute().stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getPrice()))
                .collect(Collectors.toList());

        logService.logApiCall(null, "Response: " + products);

        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        logService.logApiCall("Request: POST /api/products - Payload: " + productRequest, null);

        ProductResponse response = new ProductResponse(
                createProductUseCase.execute(productRequest).getId(),
                productRequest.getName(),
                productRequest.getPrice()
        );

        logService.logApiCall(null, "Response: " + response);

        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        logService.logApiCall("Request: PUT /api/products/" + id + " - Payload: " + productRequest, null);

        Product updatedProduct = updateProductUseCase.execute(id, productRequest);

        ProductResponse response = new ProductResponse(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getPrice()
        );

        logService.logApiCall(null, "Response: " + response);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        logService.logApiCall("Request: DELETE /api/products/" + id, null);

        deleteProductUseCase.execute(id);

        logService.logApiCall(null, "Response: No Content");

        return ResponseEntity.noContent().build();
    }
}
