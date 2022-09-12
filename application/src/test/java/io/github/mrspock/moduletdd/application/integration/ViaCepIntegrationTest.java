package io.github.mrspock.moduletdd.application.integration;

import io.github.mrspock.moduletdd.domain.entity.Address;
import io.github.mrspock.moduletdd.domain.exception.BadRequestException;
import io.github.mrspock.moduletdd.domain.exception.NotFoundException;
import io.github.mrspock.moduletdd.domain.integration.FindCepIntegration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ViaCepIntegrationTest {

    @Autowired
    private FindCepIntegration integration;

    @Test
    void when_request_via_cep_is_success_test() {
        Address address = integration.findByCep("06449380");
        assertNotNull(address);
    }

    @Test
    void when_request_via_cep_is_bad_request_test() {
        assertThrows(BadRequestException.class, () ->
                integration.findByCep("064493800"));
    }

    @Test
    void when_request_via_cep_is_not_found_test() {
        assertThrows(NotFoundException.class, () ->
                integration.findByCep("06449399"));
    }
}