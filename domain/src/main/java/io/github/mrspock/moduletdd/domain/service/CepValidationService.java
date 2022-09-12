package io.github.mrspock.moduletdd.domain.service;

import io.github.mrspock.moduletdd.domain.exception.BadRequestException;

public class CepValidationService {
    public void validate(final String cep) {
        if (cep.length() != 8 ||
                !cep.matches("[0-9]+") ||
                cep.equals("00000000") ||
                cep.equals("11111111") ||
                cep.equals("22222222") ||
                cep.equals("33333333") ||
                cep.equals("44444444") ||
                cep.equals("55555555") ||
                cep.equals("66666666") ||
                cep.equals("77777777") ||
                cep.equals("88888888") ||
                cep.equals("99999999")) {
            throw new BadRequestException("CEP invalid: " + cep);
        }
    }

}
