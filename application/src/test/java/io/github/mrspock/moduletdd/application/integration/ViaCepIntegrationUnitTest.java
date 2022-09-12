package io.github.mrspock.moduletdd.application.integration;

import io.github.mrspock.moduletdd.application.integration.adapter.FindCepAdapter;
import io.github.mrspock.moduletdd.application.integration.client.FindCepIntegrationByViaCep;
import io.github.mrspock.moduletdd.application.integration.dto.ViaCepResponse;
import io.github.mrspock.moduletdd.domain.entity.Address;
import io.github.mrspock.moduletdd.domain.exception.InternalServerError;
import io.github.mrspock.moduletdd.domain.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ViaCepIntegrationUnitTest {

    @InjectMocks
    FindCepIntegrationImpl integration;

    @Mock
    FindCepAdapter adapter;

    @Mock
    FindCepIntegrationByViaCep client;

    private Address mockAddress() {
        return new Address(
                "06449380",
                "Rua Potirendaba",
                "Parque Viana",
                "Barueri",
                "Sao Paulo",
                ""
        );
    }

    private ViaCepResponse mockViaCepResponse() {
        return new ViaCepResponse(
                "06449-380",
                "Rua Potirendaba",
                "",
                "Parque Viana",
                "Barueri",
                "SP",
                "3505708",
                "2069",
                "11",
                "6213",
                true
        );
    }

    @Test
    void find_cep_with_return_success_test() {
        when(client.findCep(anyString())).thenReturn(mockViaCepResponse());
        when(adapter.cast(any(ViaCepResponse.class))).thenReturn(mockAddress());

        Address address = integration.findByCep("06449380");
        assertEquals(mockAddress(), address);

        verify(client).findCep(anyString());
        verify(adapter).cast(any(ViaCepResponse.class));
    }

    @Test
    void find_cep_with_not_exists_test() {
        when(client.findCep(anyString())).thenThrow(new NotFoundException());
        assertThrows(NotFoundException.class, () ->
                integration.findByCep("06449380"));
        verify(client).findCep(anyString());
    }

    @Test
    void internal_server_error_request_test() {
        when(client.findCep(anyString())).thenThrow(new InternalServerError());
        assertThrows(InternalServerError.class, () ->
                integration.findByCep("06449380"));
        verify(client).findCep(anyString());
    }

}