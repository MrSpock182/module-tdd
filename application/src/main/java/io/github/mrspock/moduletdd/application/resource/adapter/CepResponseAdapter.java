package io.github.mrspock.moduletdd.application.resource.adapter;

import io.github.mrspock.moduletdd.application.resource.dto.CepResponse;
import io.github.mrspock.moduletdd.domain.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class CepResponseAdapter {
    public CepResponse cast(final Address address) {
        return new CepResponse(
                address.cep(),
                address.name(),
                address.neighborhood(),
                address.city(),
                address.state(),
                address.complement()
        );
    }
}
