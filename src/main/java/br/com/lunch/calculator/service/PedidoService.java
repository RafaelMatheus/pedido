package br.com.lunch.calculator.service;

import br.com.lunch.calculator.entity.request.PedidoRequest;
import br.com.lunch.calculator.entity.response.CalculaPedidoResponse;
import br.com.lunch.calculator.entity.response.PedidoResponse;
import br.com.lunch.calculator.exception.NotFoundException;

public interface PedidoService {
    CalculaPedidoResponse calcularValorPedido(final String codigoPagamento) throws NotFoundException;

    PedidoResponse criarPedido(final PedidoRequest pedido);
}
