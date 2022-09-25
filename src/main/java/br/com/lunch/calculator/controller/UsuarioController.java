package br.com.lunch.calculator.controller;

import br.com.lunch.calculator.entity.request.UsuarioRequest;
import br.com.lunch.calculator.entity.response.UsuarioResponse;
import br.com.lunch.calculator.test.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    private final UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@RequestBody @Valid final UsuarioRequest usuarioRequest) {
        this.service.criarUsuario(usuarioRequest);
        return ResponseEntity.ok().build();
    }
}
