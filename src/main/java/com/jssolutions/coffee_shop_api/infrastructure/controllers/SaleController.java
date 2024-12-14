package com.jssolutions.coffee_shop_api.infrastructure.controllers;

import com.jssolutions.coffee_shop_api.application.dto.SaleRequest;
import com.jssolutions.coffee_shop_api.application.dto.SaleResponse;
import com.jssolutions.coffee_shop_api.application.usecase.CreateSaleUseCase;
import com.jssolutions.coffee_shop_api.application.usecase.ListSalesUseCase;
import com.jssolutions.coffee_shop_api.domain.entities.Address;
import com.jssolutions.coffee_shop_api.domain.entities.Sale;
import com.jssolutions.coffee_shop_api.domain.services.CepService;
import com.jssolutions.coffee_shop_api.infrastructure.services.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    private final CreateSaleUseCase createSaleUseCase;
    private final ListSalesUseCase listSalesUseCase;
    private final CepService cepService;
    private final LogService logService;

    public SaleController(CreateSaleUseCase createSaleUseCase, ListSalesUseCase listSalesUseCase, CepService cepService, LogService logService) {
        this.createSaleUseCase = createSaleUseCase;
        this.listSalesUseCase = listSalesUseCase;
        this.cepService = cepService;
        this.logService = logService;
    }

    @GetMapping
    public ResponseEntity<List<SaleResponse>> getAllSales() {
        logService.logApiCall("Request: GET /api/sales", null);
        List<SaleResponse> sales = listSalesUseCase.execute().stream().map(sale -> new SaleResponse(
                sale.getId(),
                sale.getUserName(),
                sale.getContactNumber(),
                sale.getProductId(),
                sale.getTotal(),
                sale.getAddress()
        )).toList();

        logService.logApiCall(null, "Response: " + sales);

        return ResponseEntity.ok(sales);
    }

    @PostMapping
    public ResponseEntity<SaleResponse> createSale(@RequestBody SaleRequest saleRequest) {
        Address address = cepService.getAddressByCep(saleRequest.getCep());
        Sale sale = createSaleUseCase.execute(saleRequest, address);

        SaleResponse saleResponse = new SaleResponse(
                sale.getId(),
                sale.getUserName(),
                sale.getContactNumber(),
                sale.getProductId(),
                sale.getTotal(),
                sale.getAddress()
        );

        logService.logApiCall("Request: POST /api/sales - Payload: " + saleRequest, null);
        logService.logApiCall(null, "Response: " + saleResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(saleResponse);
    }

}
