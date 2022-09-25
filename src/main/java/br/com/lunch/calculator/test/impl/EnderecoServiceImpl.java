package br.com.lunch.calculator.test.impl;

import br.com.lunch.calculator.entity.EnderecoEntity;
import br.com.lunch.calculator.entity.request.EnderecoRequest;
import br.com.lunch.calculator.entity.response.EnderecoResponse;
import br.com.lunch.calculator.mapper.EnderecoMapper;
import br.com.lunch.calculator.repository.EnderecoRepository;
import br.com.lunch.calculator.test.EnderecoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EnderecoServiceImpl implements EnderecoService {
    private final EnderecoRepository repository;
    private final EnderecoMapper mapper;

    @Override
    public EnderecoResponse criarEndereco(final EnderecoRequest endereco) {
        log.info("Realizando cadastro de um endereço");
        EnderecoEntity save = repository.save(this.mapper.toEntity(endereco));
        log.info("Endereco cadastrado com sucesso com o ID: '{}'", save.getId());
        return this.mapper.toResponse(save);
    }
}
