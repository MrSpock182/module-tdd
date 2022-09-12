package io.github.mrspock.moduletdd.application.integration;

import io.github.mrspock.moduletdd.application.integration.adapter.FindCepAdapter;
import io.github.mrspock.moduletdd.application.integration.client.FindCepIntegrationByViaCep;
import io.github.mrspock.moduletdd.application.integration.dto.ViaCepResponse;
import io.github.mrspock.moduletdd.domain.entity.Address;
import io.github.mrspock.moduletdd.domain.exception.NotFoundException;
import io.github.mrspock.moduletdd.domain.integration.FindCepIntegration;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;

@Component
public class FindCepIntegrationImpl implements FindCepIntegration {

    private final FindCepAdapter adapter;
    private final FindCepIntegrationByViaCep client;

    public FindCepIntegrationImpl(
            FindCepAdapter adapter,
            FindCepIntegrationByViaCep client) {
        this.adapter = adapter;
        this.client = client;
    }

    @Override
    @Retry(name = "find-cep-retry")
    @Bulkhead(name = "find-cep")
    @CircuitBreaker(name = "find-cep-circuit-breaker")
    public Address findByCep(final String cep) {
        ViaCepResponse response = client.findCep(cep);
        if (Boolean.TRUE.equals(response.erro())) {
            throw new NotFoundException("Not Found CEP: " + cep);
        }
        return adapter.cast(response);
    }
}
