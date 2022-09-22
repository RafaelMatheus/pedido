package br.com.lunch.calculator.service.impl;

import br.com.lunch.calculator.entity.ItemPedido;
import br.com.lunch.calculator.entity.request.PedidoRequest;
import br.com.lunch.calculator.entity.response.CalculaPedidoResponse;
import br.com.lunch.calculator.entity.response.PedidoResponse;
import br.com.lunch.calculator.helper.GerarCodigoProvider;
import br.com.lunch.calculator.repository.PedidoRespository;
import br.com.lunch.calculator.service.PedidoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRespository respository;
    private final Map<String, GerarCodigoProvider> providers;

    public PedidoServiceImpl(final Set<GerarCodigoProvider> providers, final PedidoRespository respository) {
        this.respository = respository;
        this.providers = generateProviderMap(providers);
    }

    @Override
    public CalculaPedidoResponse calcularValorPedido(final UUID pedidoId) throws Exception {
        final var pedido = this.respository
                .findById(pedidoId)
                .orElseThrow(() -> new Exception());

        var totalPedido = pedido.getAcrescimo();

        BigDecimal totalItensPedidos = pedido.getItens()
                .stream()
                .map(ItemPedido::getPreco)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        totalPedido = totalPedido.add(pedido.getValorEntrega())
                .add(totalItensPedidos);

        return CalculaPedidoResponse.builder()
                .valorTotalCompra(totalPedido)
                .valorTotalPorPessoa(BigDecimal.ZERO)
                .build();
    }

    @Override
    public PedidoResponse criarPedido(final PedidoRequest pedido) {
        return null;
    }

    private BigDecimal calculaPercentual(final BigDecimal valorTotal, final BigDecimal valorAtual){
        return valorAtual.multiply(valorTotal).divide(new BigDecimal(100));
    }

    private static Map<String, GerarCodigoProvider> generateProviderMap(Set<GerarCodigoProvider> providers) {
        Map<String, GerarCodigoProvider> providerMap = new HashMap<>();
        providers.stream().forEach(provider -> providerMap.put(provider.getEmpresa(), provider));
        return providerMap;
    }

}
