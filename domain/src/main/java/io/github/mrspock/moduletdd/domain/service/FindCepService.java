package io.github.mrspock.moduletdd.domain.service;

import io.github.mrspock.moduletdd.domain.entity.Address;
import io.github.mrspock.moduletdd.domain.integration.FindCepIntegration;

public class FindCepService {

    private final FindCepIntegration integration;
    private final CepValidationService cepValidationService;

    public FindCepService(
            FindCepIntegration integration,
            CepValidationService cepValidationService) {
        this.integration = integration;
        this.cepValidationService = cepValidationService;
    }

    public Address findCep(final String cep) {
        cepValidationService.validate(cep);
        return integration.findByCep(cep);
    }

}
