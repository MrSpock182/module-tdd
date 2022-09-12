package io.github.mrspock.moduletdd.domain.integration;

import io.github.mrspock.moduletdd.domain.entity.Address;

public interface FindCepIntegration {
    Address findByCep(String cep);
}
