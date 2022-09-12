package io.github.mrspock.moduletdd.application.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CepResponse(
        @JsonProperty("cep")
        String cep,
        @JsonProperty("logradouro")
        String name,
        @JsonProperty("bairro")
        String neighborhood,
        @JsonProperty("cidade")
        String city,
        @JsonProperty("uf")
        String state,
        @JsonProperty("complemento")
        String complement
) {
}
