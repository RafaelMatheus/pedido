package br.com.lunch.calculator.service;

import br.com.lunch.calculator.entity.response.CalculaPedidoResponse;
import br.com.lunch.calculator.entity.response.PedidoResponse;
import br.com.lunch.calculator.entity.request.PedidoRequest;

import java.util.UUID;

public interface PedidoService {
    CalculaPedidoResponse calcularValorPedido(UUID pedidoId) throws Exception;

    PedidoResponse criarPedido(PedidoRequest pedido);
}
