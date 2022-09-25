package br.com.lunch.calculator.test;

import br.com.lunch.calculator.entity.request.UsuarioRequest;
import br.com.lunch.calculator.entity.response.UsuarioResponse;

public interface UsuarioService {
    UsuarioResponse criarUsuario(final UsuarioRequest usuario);
}
