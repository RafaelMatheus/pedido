package br.com.lunch.calculator.service.impl;

import br.com.lunch.calculator.entity.ItemPedido;
import br.com.lunch.calculator.entity.PedidoEntity;
import br.com.lunch.calculator.entity.UsuarioEntity;
import br.com.lunch.calculator.entity.request.PedidoRequest;
import br.com.lunch.calculator.entity.response.CalculaPedidoResponse;
import br.com.lunch.calculator.entity.response.PedidoResponse;
import br.com.lunch.calculator.entity.response.ValorPorPessoaResponse;
import br.com.lunch.calculator.helper.GerarCodigoProvider;
import br.com.lunch.calculator.mapper.UsuarioMapper;
import br.com.lunch.calculator.repository.PedidoRespository;
import br.com.lunch.calculator.service.PedidoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRespository respository;
    private final UsuarioMapper usuarioMapper;
    private final Map<String, GerarCodigoProvider> providers;

    public PedidoServiceImpl(final Set<GerarCodigoProvider> providers, final PedidoRespository respository, UsuarioMapper usuarioMapper) {
        this.respository = respository;
        this.providers = generateProviderMap(providers);
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public CalculaPedidoResponse calcularValorPedido(final UUID pedidoId) throws Exception {
        final PedidoEntity pedido = this.respository
                .findById(pedidoId)
                .orElseThrow(() -> new Exception());

        BigDecimal totalPedido = pedido.getAcrescimo();

        BigDecimal totalItensPedidos = pedido.getItens()
                .stream()
                .map(ItemPedido::getPreco)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        totalPedido = totalPedido.add(pedido.getValorEntrega())
                .add(totalItensPedidos);

        Map<UsuarioEntity, List<ItemPedido>> listaDePedidosPorUsuario = pedido.getItens()
                .stream()
                .collect(Collectors.groupingBy(ItemPedido::getUsuario));

        CalculaPedidoResponse toResponse = CalculaPedidoResponse.builder()
                .valorTotalCompra(totalPedido)
                .valorTotalPorPessoa(new ArrayList<>())
                .build();

        BigDecimal finalTotalPedido = totalPedido;

        listaDePedidosPorUsuario.forEach((key, value) -> {

            BigDecimal totalPorPessoa = listaDePedidosPorUsuario.get(key).stream()
                    .map(ItemPedido::getPreco)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO);

            BigDecimal percentualPagarPessoa = this.calculaPercentual(finalTotalPedido, totalPorPessoa);

            ValorPorPessoaResponse valorPorPessoa = ValorPorPessoaResponse
                    .builder()
                    .usuarioResponse(usuarioMapper.toResponse(key))
                    .percentualTotal(percentualPagarPessoa)
                    .valor(this.calculaValorPercentual(totalPorPessoa, percentualPagarPessoa))
                    .build();

            toResponse.getValorTotalPorPessoa().add(valorPorPessoa);
        });

        return toResponse;
    }

    @Override
    public PedidoResponse criarPedido(final PedidoRequest pedido) {
        return null;
    }

    private BigDecimal calculaPercentual(final BigDecimal valorTotal, final BigDecimal valorAtual){
        return valorAtual.divide(valorTotal).multiply(new BigDecimal(100));
    }

    private BigDecimal calculaValorPercentual(final BigDecimal totalPorPessoa, final BigDecimal percentualPagarPessoa) {
        return totalPorPessoa.divide(percentualPagarPessoa);
    }

    private static Map<String, GerarCodigoProvider> generateProviderMap(final Set<GerarCodigoProvider> providers) {
        Map<String, GerarCodigoProvider> providerMap = new HashMap<>();
        providers.stream().forEach(provider -> providerMap.put(provider.getEmpresa(), provider));
        return providerMap;
    }

}
