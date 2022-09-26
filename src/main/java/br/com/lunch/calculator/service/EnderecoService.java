package br.com.lunch.calculator.service;

import br.com.lunch.calculator.entity.request.EnderecoRequest;
import br.com.lunch.calculator.entity.response.EnderecoResponse;

public interface EnderecoService {
    EnderecoResponse criarEndereco(final EnderecoRequest endereco);
}
