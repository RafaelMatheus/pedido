package br.com.lunch.calculator.service.service;

import br.com.lunch.calculator.entity.EnderecoEntity;
import br.com.lunch.calculator.entity.ItemPedido;
import br.com.lunch.calculator.entity.PedidoEntity;
import br.com.lunch.calculator.entity.UsuarioEntity;
import br.com.lunch.calculator.entity.response.CalculaPedidoResponse;
import br.com.lunch.calculator.exception.NotFoundException;
import br.com.lunch.calculator.helper.GerarCodigoProvider;
import br.com.lunch.calculator.mapper.UsuarioMapper;
import br.com.lunch.calculator.repository.PedidoRespository;
import br.com.lunch.calculator.service.impl.PedidoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceImplTest {
    @Mock
    private PedidoRespository respository;

    @Mock
    private Set<GerarCodigoProvider> providers;

    @Mock
    private UsuarioMapper mapper;

    @InjectMocks
    private PedidoServiceImpl service;


    @Test
    public void deveLancarExceptionQuandoNaoEncontrarPedidoPorCodigo() {
        when(this.respository.findByCodigoPagamento(any())).thenReturn(Optional.ofNullable(null));

        assertThrows(NotFoundException.class, () -> this.service.calcularValorPedido(UUID.randomUUID().toString()));
    }

    @Test
    public void deveSomarTotalPedidoCompradorUnicoSemDescontoETaxaEntrega() {
        when(this.respository.findByCodigoPagamento(any())).thenReturn(Optional.of(getPedidoCompradorUnico(null, null)));

        final CalculaPedidoResponse response = this.service.calcularValorPedido(UUID.randomUUID().toString());

        assertAll(() -> assertEquals(new BigDecimal(10), response.getValorTotalCompra()),
                () -> assertEquals(new BigDecimal("10.00"), response.getValorTotalPorPessoa().get(0).getValor()),
                () -> assertEquals("Rafael", response.getValorTotalPorPessoa().get(0).getUsuario()));
    }

    @Test
    public void deveSomarTotalPedidoCompradorUnico() {
        when(this.respository.findByCodigoPagamento(any())).thenReturn(Optional.of(getPedidoCompradorUnico(BigDecimal.TEN, BigDecimal.TEN)));

        final CalculaPedidoResponse response = this.service.calcularValorPedido(UUID.randomUUID().toString());

        assertAll(() -> assertEquals(new BigDecimal(10), response.getValorTotalCompra()),
                () -> assertEquals(new BigDecimal("10.00"), response.getValorTotalPorPessoa().get(0).getValor()),
                () -> assertEquals("Rafael", response.getValorTotalPorPessoa().get(0).getUsuario()));
    }

    @Test
    public void deveSomarTotalPedidoMultiplosCompradores() throws Exception {
        when(this.respository.findByCodigoPagamento(any())).thenReturn(Optional.of(getPedidoCompradorMultiplos()));

        final CalculaPedidoResponse response = this.service.calcularValorPedido(UUID.randomUUID().toString());

        assertAll(() -> assertEquals(response.getValorTotalCompra(), new BigDecimal(50)),
                () -> assertNotNull(response.getCodigoPedido()),
                () -> assertNotNull(response.getLinkPagamento()),
                () -> assertEquals(new BigDecimal("8.00"), response.getValorTotalPorPessoa().get(0).getValor()),
                () -> assertEquals(new BigDecimal("6.08"), response.getValorTotalPorPessoa().get(0).getValorDesconto()),
                () -> assertEquals(new BigDecimal("42.00"), response.getValorTotalPorPessoa().get(1).getValor()),
                () -> assertEquals(new BigDecimal("31.92"), response.getValorTotalPorPessoa().get(1).getValorDesconto()),
                () -> assertEquals(new BigDecimal("31.92"), response.getValorTotalPorPessoa().get(1).getValorDesconto()));

    }

    private PedidoEntity getPedidoCompradorUnico(final BigDecimal desconto, final BigDecimal entrega) {
        ItemPedido item1 = ItemPedido.builder().nome("Hamburger")
                .preco(BigDecimal.TEN)
                .dataHoraCriacao(LocalDateTime.now())
                .usuario(UsuarioEntity.builder().id(UUID.randomUUID().toString())
                        .nome("Rafael")
                        .endereco(EnderecoEntity.builder().build()).build()).build();

        return PedidoEntity.builder().desconto(desconto).valorEntrega(entrega)
                .itens(Collections.singletonList(item1)).build();
    }

    private PedidoEntity getPedidoCompradorMultiplos() {
        final String idRafael = UUID.randomUUID().toString();
        ItemPedido itemRafael1 = ItemPedido.builder().nome("Hamburger")
                .preco(new BigDecimal(40))
                .dataHoraCriacao(LocalDateTime.now())
                .usuario(UsuarioEntity.builder().id(idRafael)
                        .nome("Rafael")
                        .endereco(EnderecoEntity.builder().build()).build()).build();

        ItemPedido itemRafael2 = ItemPedido.builder()
                .nome("Sobremesa").preco(new BigDecimal(2))
                .dataHoraCriacao(LocalDateTime.now())
                .usuario(UsuarioEntity.builder().id(idRafael)
                        .nome("Rafael")
                        .endereco(EnderecoEntity.builder().build()).build()).build();

        ItemPedido item2 = ItemPedido.builder()
                .nome("Saduiche")
                .preco(new BigDecimal(8))
                .dataHoraCriacao(LocalDateTime.now())
                .usuario(UsuarioEntity.builder().id(UUID.randomUUID().toString())
                        .nome("Matheus")
                        .endereco(EnderecoEntity.builder().build()).build()).build();

        return PedidoEntity.builder()
                .desconto(new BigDecimal(20))
                .valorEntrega(new BigDecimal(8))
                .codigoPagamento(UUID.randomUUID().toString())
                .linkPagamento("link para o pagamento")
                .itens(Arrays.asList(itemRafael1, itemRafael2, item2)).build();
    }
}
