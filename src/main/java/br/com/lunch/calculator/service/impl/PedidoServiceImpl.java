package br.com.lunch.calculator.service.impl;

import br.com.lunch.calculator.entity.ItemPedido;
import br.com.lunch.calculator.entity.PedidoEntity;
import br.com.lunch.calculator.entity.UsuarioEntity;
import br.com.lunch.calculator.entity.request.PedidoRequest;
import br.com.lunch.calculator.entity.request.enums.FormaPagamentoEnum;
import br.com.lunch.calculator.entity.response.CalculaPedidoResponse;
import br.com.lunch.calculator.entity.response.PedidoResponse;
import br.com.lunch.calculator.entity.response.ValorPorPessoaResponse;
import br.com.lunch.calculator.exception.NotFoundException;
import br.com.lunch.calculator.helper.GerarCodigoProvider;
import br.com.lunch.calculator.mapper.PedidoMapper;
import br.com.lunch.calculator.repository.PedidoRespository;
import br.com.lunch.calculator.service.PedidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRespository respository;
    private final PedidoMapper mapper;
    private final Map<FormaPagamentoEnum, GerarCodigoProvider> providers;

    public PedidoServiceImpl(final Set<GerarCodigoProvider> providers, final PedidoRespository respository, final PedidoMapper mapper) {
        this.respository = respository;
        this.providers = generateProviderMap(providers);
        this.mapper = mapper;
    }

    @Override
    public PedidoResponse criarPedido(final PedidoRequest pedido) {
        String codigoPedido = this.gerarIdentificadorPagamento();

        log.info("Cadastrando um pedido com código '{}'", codigoPedido);
        PedidoEntity entity = this.mapper.toEntity(pedido);
        entity.setCodigoPagamento(codigoPedido);
        entity.setLinkPagamento(this.providers.get(pedido.getFormaPagamento()).gerarLinkPagamento(entity));
        return this.mapper.toResponse(this.respository.save(entity));
    }

    @Override
    public CalculaPedidoResponse calcularValorPedido(final String codigoPedido) throws NotFoundException {
        log.info("Realizando calculo do pedido com código '{}'", codigoPedido);
        final PedidoEntity pedido = this.respository
                .findByCodigoPagamento(codigoPedido)
                .orElseThrow(() -> new NotFoundException());

        BigDecimal totalItensPedidos = getTotalItensPedidos(pedido);

        Map<UsuarioEntity, List<ItemPedido>> listaDePedidosPorUsuario = pedido.getItens()
                .stream()
                .collect(Collectors.groupingBy(ItemPedido::getUsuario));

        CalculaPedidoResponse toResponse = CalculaPedidoResponse.builder()
                .valorTotalCompra(totalItensPedidos)
                .valorTotalPorPessoa(new ArrayList<>())
                .codigoPedido(pedido.getCodigoPagamento())
                .linkPagamento(pedido.getLinkPagamento())
                .build();

        listaDePedidosPorUsuario.forEach((key, value) -> {

            BigDecimal totalPorPessoa = listaDePedidosPorUsuario.get(key).stream()
                    .map(ItemPedido::getPreco)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO);

            BigDecimal percentualPagarPessoa = this.calculaPercentual(totalItensPedidos, totalPorPessoa);

            BigDecimal descontoAplicadoProporcional = this.calculaValorPercentual(pedido.getDesconto(), percentualPagarPessoa);

            BigDecimal adicaoProposcionalTaxaDeServicoAplicada = this.calculaValorPercentual(pedido.getValorEntrega(), percentualPagarPessoa);

            BigDecimal valotTotal = this.calculaValorPercentual(totalItensPedidos, percentualPagarPessoa);

            BigDecimal valorPercentualGarcom = this.calculaValorPercentual(totalItensPedidos, pedido.getPercentualAcrescimo());

            BigDecimal adicaoValorRealProporcional = this.calculaValorPercentual(pedido.getAcrescimoReal(), percentualPagarPessoa);

            valotTotal.add(valorPercentualGarcom.divide(BigDecimal.valueOf(listaDePedidosPorUsuario.size())))
                    .add(adicaoValorRealProporcional);

            ValorPorPessoaResponse valorPorPessoa = ValorPorPessoaResponse
                    .builder()
                    .usuario(key.getNome())
                    .percentualTotal(percentualPagarPessoa)
                    .valorDesconto(valotTotal.add(adicaoProposcionalTaxaDeServicoAplicada).subtract(descontoAplicadoProporcional))
                    .valor(valotTotal)
                    .build();

            toResponse.getValorTotalPorPessoa().add(valorPorPessoa);
        });

        return toResponse;
    }

    private static BigDecimal getTotalItensPedidos(PedidoEntity pedido) {
        return pedido.getItens()
                .stream()
                .map(ItemPedido::getPreco)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private String gerarIdentificadorPagamento() {
        return UUID.randomUUID().toString();
    }

    private BigDecimal calculaPercentual(final BigDecimal valorTotal, final BigDecimal valorAtual) {
        return valorAtual.divide(valorTotal, MathContext.DECIMAL64).multiply(new BigDecimal(100));
    }

    private BigDecimal calculaValorPercentual(final BigDecimal valor, final BigDecimal percentual) {
        if (Objects.isNull(valor) || Objects.isNull(percentual)) {
            return BigDecimal.ZERO;
        }
        return valor.multiply(percentual.divide(new BigDecimal(100)))
                .setScale(2, RoundingMode.HALF_DOWN);
    }

    private static Map<FormaPagamentoEnum, GerarCodigoProvider> generateProviderMap(final Set<GerarCodigoProvider> providers) {
        Map<FormaPagamentoEnum, GerarCodigoProvider> providerMap = new HashMap<>();
        providers.stream().forEach(provider -> providerMap.put(provider.getEmpresa(), provider));
        return providerMap;
    }

}
