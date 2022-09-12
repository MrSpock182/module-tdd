package io.github.mrspock.moduletdd.domain.service;

import io.github.mrspock.moduletdd.domain.exception.BadRequestException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CepValidationTest {

    private final CepValidationService service = new CepValidationService();

    @Test
    void validation_cep_correct_test() {
        service.validate("06449380");
        assertTrue(true);
    }

    @Test
    void validation_cep_more_characters_test() {
        assertThrows(BadRequestException.class, () ->
                service.validate("0644938000"));
    }

    @Test
    void validation_cep_less_characters_test() {
        assertThrows(BadRequestException.class, () ->
                service.validate("0644938"));
    }

    @Test
    void validation_cep_same_characters_test() {
        assertThrows(BadRequestException.class, () ->
                service.validate("00000000"));
    }

    @Test
    void validation_cep_if_characters_is_not_number() {
        assertThrows(BadRequestException.class, () ->
                service.validate("06449-38"));
    }

}