package com.jssolutions.coffee_shop_api.infrastructure.repositories;

import com.jssolutions.coffee_shop_api.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
