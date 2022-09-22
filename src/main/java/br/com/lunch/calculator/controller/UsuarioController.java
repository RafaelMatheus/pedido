package br.com.lunch.calculator.controller;

import br.com.lunch.calculator.entity.request.UsuarioRequest;
import br.com.lunch.calculator.entity.response.UsuarioResponse;
import br.com.lunch.calculator.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    private final UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@RequestBody final UsuarioRequest usuarioRequest){
        this.service.criarUsuario(usuarioRequest);
        return ResponseEntity.ok().build();
    }
}
