package br.com.lunch.calculator.mapper;

import br.com.lunch.calculator.entity.EnderecoEntity;
import br.com.lunch.calculator.entity.request.EnderecoRequest;
import br.com.lunch.calculator.entity.response.EnderecoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoEntity toEntity(final EnderecoRequest endereco);

    EnderecoResponse toResponse(final EnderecoEntity endereco);
}
