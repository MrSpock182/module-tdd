package io.github.mrspock.moduletdd.application.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class FindCepApiTest {

    private final String context = "/module-tdd/v1";

    @Autowired
    private FindCepResource resource;

    private ObjectMapper mapper() {
        return JsonMapper.builder()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .findAndAddModules()
                .build();
    }

    private MockMvc mockMvc() {
        return MockMvcBuilders
                .standaloneSetup(resource)
//                .setControllerAdvice(new RestExceptionHandler(logger))
                .build();
    }

    @Test
    void when_request_is_200_test() throws Exception {
        mockMvc().perform(get(context)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void when_request_is_400_test() throws Exception {
        mockMvc().perform(get(context)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void when_request_is_404_test() throws Exception {
        mockMvc().perform(get(context)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void when_request_is_500_test() throws Exception {
        mockMvc().perform(get(context)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

}
