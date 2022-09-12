package io.github.mrspock.moduletdd.application.integration.adapter;

import io.github.mrspock.moduletdd.application.integration.dto.ViaCepResponse;
import io.github.mrspock.moduletdd.domain.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class FindCepAdapter {

    public Address cast(final ViaCepResponse response) {
        return new Address(
                response.cep(),
                response.logradouro(),
                response.bairro(),
                response.localidade(),
                response.uf(),
                response.complemento()
        );
    }
}