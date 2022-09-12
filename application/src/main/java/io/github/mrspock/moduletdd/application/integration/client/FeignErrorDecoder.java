package io.github.mrspock.moduletdd.application.integration.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import io.github.mrspock.moduletdd.domain.exception.BadRequestException;
import io.github.mrspock.moduletdd.domain.exception.InternalServerError;
import io.github.mrspock.moduletdd.domain.exception.NotFoundException;
import io.github.mrspock.moduletdd.domain.exception.TimeoutException;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(final String s, final Response response) {
        return switch (response.status()) {
            case 400 -> new BadRequestException(response.toString());
            case 404 -> new NotFoundException(response.toString());
            case 408 | 504 -> new TimeoutException(response.toString());
            default -> new InternalServerError(response.toString());
        };
    }
}
