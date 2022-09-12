package io.github.mrspock.moduletdd.domain.entity;

public record Address(
        String cep,
        String name,
        String neighborhood,
        String city,
        String state,
        String complement
) {
}
