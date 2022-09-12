package io.github.mrspock.moduletdd.application.resource;

import io.github.mrspock.moduletdd.application.resource.adapter.CepResponseAdapter;
import io.github.mrspock.moduletdd.application.resource.dto.CepResponse;
import io.github.mrspock.moduletdd.domain.service.FindCepService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/module-tdd/v1")
public class FindCepResource {

    private final FindCepService service;
    private final CepResponseAdapter adapter;

    public FindCepResource(FindCepService service, CepResponseAdapter adapter) {
        this.service = service;
        this.adapter = adapter;
    }

    @ResponseStatus(OK)
    @GetMapping("/find/{cep}")
    public CepResponse findCep(@PathVariable("cep") final String cep) {
        return adapter.cast(service.findCep(cep));
    }

}