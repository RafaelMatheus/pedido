package br.com.lunch.calculator.controller;

import br.com.lunch.calculator.entity.request.EnderecoRequest;
import br.com.lunch.calculator.entity.response.EnderecoResponse;
import br.com.lunch.calculator.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("enderecos")
@RestController
@RequiredArgsConstructor
public class EnderecoController {
    private final EnderecoService service;

    @PostMapping
    public ResponseEntity<EnderecoResponse> criarEndereco(final @RequestBody @Valid EnderecoRequest endereco) {
        return ResponseEntity.ok(service.criarEndereco(endereco));
    }
}
