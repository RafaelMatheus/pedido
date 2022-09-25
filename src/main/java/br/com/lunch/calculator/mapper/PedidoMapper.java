package br.com.lunch.calculator.mapper;

import br.com.lunch.calculator.entity.ItemPedido;
import br.com.lunch.calculator.entity.PedidoEntity;
import br.com.lunch.calculator.entity.request.ItemPedidoRequest;
import br.com.lunch.calculator.entity.request.PedidoRequest;
import br.com.lunch.calculator.entity.response.PedidoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mapping(source = "enderecoEntregaId", target = "enderecoEntrega.id")
    PedidoEntity toEntity(final PedidoRequest pedido);

    @Mapping(source = "itemPedido.usuarioId", target = "usuario.id")
    ItemPedido map (final ItemPedidoRequest itemPedido);

    @Mapping(target = "enderecoEntregaId", source = "enderecoEntrega.id")
    PedidoResponse toResponse(final PedidoEntity pedido);
}
