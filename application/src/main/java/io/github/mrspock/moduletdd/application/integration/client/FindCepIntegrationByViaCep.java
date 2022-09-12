package io.github.mrspock.moduletdd.application.integration.client;

import io.github.mrspock.moduletdd.application.configuration.FeignClientConfiguration;
import io.github.mrspock.moduletdd.application.integration.dto.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "ViaCep",
        url = "https://viacep.com.br",
//        url = "${feign.client.config.via-cep.url}",
        configuration = FeignClientConfiguration.class
)
public interface FindCepIntegrationByViaCep {
    @GetMapping(path = "/ws/{cep}/json/")
    ViaCepResponse findCep(@RequestParam(value = "cep") String cep);
}