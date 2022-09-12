package io.github.mrspock.moduletdd.application.configuration;

import io.github.mrspock.moduletdd.application.integration.client.FeignErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {
    @Bean
    public FeignErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }
}
