package br.com.lunch.calculator.service.impl;

import br.com.lunch.calculator.entity.UsuarioEntity;
import br.com.lunch.calculator.entity.request.UsuarioRequest;
import br.com.lunch.calculator.entity.response.UsuarioResponse;
import br.com.lunch.calculator.mapper.UsuarioMapper;
import br.com.lunch.calculator.repository.UsuarioRepository;
import br.com.lunch.calculator.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    @Override
    public UsuarioResponse criarUsuario(final UsuarioRequest usuario) {
        UsuarioEntity save = this.repository.save(this.mapper.toEntity(usuario));
        return this.mapper.toResponse(save);
    }
}
