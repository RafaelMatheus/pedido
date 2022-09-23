package br.com.lunch.calculator.mapper;

import br.com.lunch.calculator.entity.UsuarioEntity;
import br.com.lunch.calculator.entity.request.UsuarioRequest;
import br.com.lunch.calculator.entity.response.UsuarioResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioResponse toResponse(final UsuarioEntity usuario);

    UsuarioRequest toRequest(final UsuarioEntity usuario);

    UsuarioEntity toEntity(final UsuarioRequest usuario);
}
