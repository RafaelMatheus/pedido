package br.com.lunch.calculator.service.impl;

import br.com.lunch.calculator.entity.request.EnderecoRequest;
import br.com.lunch.calculator.entity.response.EnderecoResponse;
import br.com.lunch.calculator.mapper.EnderecoMapper;
import br.com.lunch.calculator.repository.EnderecoRepository;
import br.com.lunch.calculator.service.EnderecoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {
    private final EnderecoRepository repository;
    private final EnderecoMapper mapper;

    @Override
    public EnderecoResponse criarEndereco(EnderecoRequest endereco) {
        return this.mapper.toResponse(repository.save(this.mapper.toEntity(endereco)));
    }
}
