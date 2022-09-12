package io.github.mrspock.moduletdd.application.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.github.mrspock.moduletdd.application.resource.adapter.CepResponseAdapter;
import io.github.mrspock.moduletdd.application.resource.handler.RestExceptionHandler;
import io.github.mrspock.moduletdd.domain.exception.BadRequestException;
import io.github.mrspock.moduletdd.domain.exception.InternalServerError;
import io.github.mrspock.moduletdd.domain.exception.NotFoundException;
import io.github.mrspock.moduletdd.domain.service.FindCepService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class FindCepApiTest {

    @Autowired
    private FindCepResource resource;

    @MockBean
    private FindCepService service;

    @MockBean
    private CepResponseAdapter adapter;

    private ObjectMapper mapper() {
        return JsonMapper.builder()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .findAndAddModules()
                .build();
    }

    private MockMvc mockMvc() {
        return MockMvcBuilders
                .standaloneSetup(resource)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    void when_request_is_200_test() throws Exception {
        mockMvc().perform(get("/module-tdd/v1/find/06449380")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void when_request_is_400_test() throws Exception {
        when(service.findCep(anyString())).thenThrow(new BadRequestException());

        mockMvc().perform(get("/module-tdd/v1/find/0644938")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(service).findCep(anyString());
    }

    @Test
    void when_request_is_404_test() throws Exception {
        when(service.findCep(anyString())).thenThrow(new NotFoundException());

        mockMvc().perform(get("/module-tdd/v1/find/06449399")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service).findCep(anyString());
    }

    @Test
    void when_request_is_500_test() throws Exception {
        when(service.findCep(anyString())).thenThrow(new InternalServerError());

        mockMvc().perform(get("/module-tdd/v1/find/06449399")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

        verify(service).findCep(anyString());
    }

}
