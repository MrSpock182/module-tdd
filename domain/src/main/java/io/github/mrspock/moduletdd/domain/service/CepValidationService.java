package io.github.mrspock.moduletdd.domain.service;

import io.github.mrspock.moduletdd.domain.exception.BadRequestException;

public class CepValidationService {

    public void validate(final String cep) {
        if (cep.length() != 9 ||
                !cep.matches("[0-9]+") ||
                cep.equals("000000000") ||
                cep.equals("111111111") ||
                cep.equals("222222222") ||
                cep.equals("333333333") ||
                cep.equals("444444444") ||
                cep.equals("555555555") ||
                cep.equals("666666666") ||
                cep.equals("777777777") ||
                cep.equals("888888888") ||
                cep.equals("999999999")) {
            throw new BadRequestException("CEP invalid: " + cep);
        }
    }

}
