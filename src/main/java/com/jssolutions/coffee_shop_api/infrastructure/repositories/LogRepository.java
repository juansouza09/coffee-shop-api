package com.jssolutions.coffee_shop_api.infrastructure.repositories;

import com.jssolutions.coffee_shop_api.domain.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}
