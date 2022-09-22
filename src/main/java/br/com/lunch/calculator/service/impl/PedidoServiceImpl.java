package br.com.lunch.calculator.service.impl;

import br.com.lunch.calculator.entity.request.PedidoRequest;
import br.com.lunch.calculator.entity.response.CalculaPedidoResponse;
import br.com.lunch.calculator.entity.response.PedidoResponse;
import br.com.lunch.calculator.helper.GerarCodigoProvider;
import br.com.lunch.calculator.service.PedidoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final Map<String, GerarCodigoProvider> providers;

    public PedidoServiceImpl(final List<GerarCodigoProvider> providers) {
        Map<String, GerarCodigoProvider> providerMap = new HashMap<>();
        providers.stream().map(provider -> providerMap.put(provider.getEmpresa(), provider));
        this.providers = providerMap;
    }

    @Override
    public CalculaPedidoResponse calcularValorPedido(final UUID pedidoId) {
        return null;
    }

    @Override
    public PedidoResponse criarPedido(PedidoRequest pedido) {
        return null;
    }

}
