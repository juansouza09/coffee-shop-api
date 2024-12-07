package com.jssolutions.coffee_shop_api.infrastructure.services;

import com.jssolutions.coffee_shop_api.domain.Address;
import com.jssolutions.coffee_shop_api.domain.services.CepService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CepServiceImpl implements CepService {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public CepServiceImpl(RestTemplate restTemplate, @Value("${api.base.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public Address getAddressByCep(String cep) {
        String url = UriComponentsBuilder.fromUriString(baseUrl + "/ws/{cep}/json/")
                .buildAndExpand(cep)
                .toUriString();

        try {
            ResponseEntity<Address> response = restTemplate.exchange(url, HttpMethod.GET, null, Address.class);
            return response.getBody();
        } catch (RestClientException e) {
            System.out.println("Erro ao chamar a API: " + e.getMessage());
            return null;
        }
    }
}
