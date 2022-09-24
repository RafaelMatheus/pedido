package br.com.lunch.calculator.service.impl;

import br.com.lunch.calculator.entity.ItemPedido;
import br.com.lunch.calculator.entity.PedidoEntity;
import br.com.lunch.calculator.entity.UsuarioEntity;
import br.com.lunch.calculator.entity.request.PedidoRequest;
import br.com.lunch.calculator.entity.response.CalculaPedidoResponse;
import br.com.lunch.calculator.entity.response.PedidoResponse;
import br.com.lunch.calculator.entity.response.ValorPorPessoaResponse;
import br.com.lunch.calculator.helper.GerarCodigoProvider;
import br.com.lunch.calculator.mapper.PedidoMapper;
import br.com.lunch.calculator.mapper.UsuarioMapper;
import br.com.lunch.calculator.repository.PedidoRespository;
import br.com.lunch.calculator.service.PedidoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
    private final PedidoMapper mapper;
    private final Map<String, GerarCodigoProvider> providers;

    public PedidoServiceImpl(final Set<GerarCodigoProvider> providers, final PedidoRespository respository, UsuarioMapper usuarioMapper, PedidoMapper mapper) {
        this.respository = respository;
        this.providers = generateProviderMap(providers);
        this.usuarioMapper = usuarioMapper;
        this.mapper = mapper;
    }

    @Override
    public CalculaPedidoResponse calcularValorPedido(final String codigoPagamento) throws Exception {
        final PedidoEntity pedido = this.respository
                .findByCodigoPagamento(codigoPagamento)
                .orElseThrow(() -> new Exception());


        BigDecimal totalItensPedidos = pedido.getItens()
                .stream()
                .map(ItemPedido::getPreco)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .add(pedido.getValorEntrega())
                .subtract(new BigDecimal(20));


        Map<UsuarioEntity, List<ItemPedido>> listaDePedidosPorUsuario = pedido.getItens()
                .stream()
                .collect(Collectors.groupingBy(ItemPedido::getUsuario));

        CalculaPedidoResponse toResponse = CalculaPedidoResponse.builder()
                .valorTotalCompra(totalItensPedidos)
                .valorTotalPorPessoa(new ArrayList<>())
                .build();

        listaDePedidosPorUsuario.forEach((key, value) -> {

            BigDecimal totalPorPessoa = listaDePedidosPorUsuario.get(key).stream()
                    .map(ItemPedido::getPreco)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO);

            BigDecimal percentualDescontoPessoa = this.calculaPercentual(totalItensPedidos, pedido.getDesconto());

            BigDecimal percentualPagarPessoa = this.calculaPercentual(totalItensPedidos, totalPorPessoa);

            BigDecimal valotTotal = this.calculaValorPercentual(totalItensPedidos, percentualPagarPessoa);
            ValorPorPessoaResponse valorPorPessoa = ValorPorPessoaResponse
                    .builder()
                    .usuario(key.getNome())
                    .percentualTotal(percentualPagarPessoa)
                    .valor(valotTotal)
                    .valorDesconto(valotTotal.subtract(this.calculaValorPercentual(valotTotal, percentualDescontoPessoa)))
                    .build();

            toResponse.getValorTotalPorPessoa().add(valorPorPessoa);
        });

        return toResponse;
    }

    @Override
    public PedidoResponse criarPedido(final PedidoRequest pedido) {
        PedidoEntity entity = this.mapper.toEntity(pedido);
        entity.setCodigoPagamento(this.gerarIdentificadorPagamento());
        return this.mapper.toResponse(this.respository.save(entity));
    }

    private String gerarIdentificadorPagamento() {
        return UUID.randomUUID().toString();
    }

    private BigDecimal calculaPercentual(final BigDecimal valorTotal, final BigDecimal valorAtual) {
        return valorAtual.divide(valorTotal, MathContext.DECIMAL64).multiply(new BigDecimal(100));
    }

    private BigDecimal calculaValorPercentual(final BigDecimal totalPorPessoa, final BigDecimal percentualPagarPessoa) {
        MathContext mathContext = new MathContext(2);
        return totalPorPessoa.multiply(percentualPagarPessoa.divide(new BigDecimal(100)))
                .setScale(2, RoundingMode.HALF_DOWN);
    }

    private static Map<String, GerarCodigoProvider> generateProviderMap(final Set<GerarCodigoProvider> providers) {
        Map<String, GerarCodigoProvider> providerMap = new HashMap<>();
        providers.stream().forEach(provider -> providerMap.put(provider.getEmpresa(), provider));
        return providerMap;
    }

}
