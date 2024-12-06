package com.jssolutions.coffee_shop_api.infrastructure.repositories;

import com.jssolutions.coffee_shop_api.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
