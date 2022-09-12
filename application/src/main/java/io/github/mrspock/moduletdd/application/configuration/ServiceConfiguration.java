package io.github.mrspock.moduletdd.application.configuration;

import io.github.mrspock.moduletdd.domain.integration.FindCepIntegration;
import io.github.mrspock.moduletdd.domain.service.CepValidationService;
import io.github.mrspock.moduletdd.domain.service.FindCepService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public CepValidationService cepValidationService() {
        return new CepValidationService();
    }

    @Bean
    public FindCepService findCepService(
            CepValidationService cepValidationService,
            FindCepIntegration findCepIntegration) {
        return new FindCepService(findCepIntegration, cepValidationService);
    }

}
