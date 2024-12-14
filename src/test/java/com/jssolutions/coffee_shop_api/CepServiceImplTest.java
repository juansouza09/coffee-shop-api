package com.jssolutions.coffee_shop_api;

import com.jssolutions.coffee_shop_api.domain.entities.Address;
import com.jssolutions.coffee_shop_api.infrastructure.services.CepServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"api.base.url=http://localhost:8081"})
public class CepServiceImplTest {

    @Autowired
    private CepServiceImpl cepService;

    @Test
    public void testGetAddressByCep() {
        Address address = cepService.getAddressByCep("05000-000");

        assertNotNull(address);
        assertEquals("05000-000", address.getCep());
        assertEquals("Rua Teste", address.getLogradouro());
        assertEquals("Bairro Teste", address.getBairro());
        assertEquals("São Paulo", address.getLocalidade());
        assertEquals("SP", address.getUf());
    }
}
