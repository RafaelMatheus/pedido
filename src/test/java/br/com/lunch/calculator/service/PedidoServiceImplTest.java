package br.com.lunch.calculator.service;

import br.com.lunch.calculator.entity.EnderecoEntity;
import br.com.lunch.calculator.entity.ItemPedido;
import br.com.lunch.calculator.entity.Pedido;
import br.com.lunch.calculator.entity.UsuarioEntity;
import br.com.lunch.calculator.helper.GerarCodigoProvider;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceImplTest {
    @Mock
    private PedidoRespository respository;

    @Mock
    private Set<GerarCodigoProvider> providers;

    @InjectMocks
    private PedidoServiceImpl service;


    @Test
    public void deveSomarTotalPedidoCompradorUnico() throws Exception {
        when(this.respository.findById(any())).thenReturn(Optional.of(getPedidoCompradorUnico()));

        final var response = this.service.calcularValorPedido(UUID.randomUUID());

        assertEquals(new BigDecimal(20), response.getValorTotalCompra());

    }

    @Test
    public void deveSomarTotalPedidoMultiplosCompradores() throws Exception {
        when(this.respository.findById(any())).thenReturn(Optional.of(getPedidoCompradorMultiplos()));

        final var response = this.service.calcularValorPedido(UUID.randomUUID());

        assertEquals(new BigDecimal(20), response.getValorTotalCompra());
    }

    private Pedido getPedidoCompradorUnico() {
        var item1 = ItemPedido.builder()
                .nome("Hamburger")
                .preco(BigDecimal.TEN)
                .dataHora(LocalDateTime.now())
                .usuario(UsuarioEntity.builder()
                        .id(UUID.randomUUID()).nome("Rafael")
                        .endereco(EnderecoEntity.builder().build()).build())
                .build();

        return Pedido.builder()
                .acrescimo(BigDecimal.ZERO)
                .valorEntrega(BigDecimal.TEN)
                .itens(Collections.singletonList(item1))
                .build();
    }

    private Pedido getPedidoCompradorMultiplos() {
        var item1 = ItemPedido.builder()
                .nome("Hamburger")
                .preco(BigDecimal.TEN)
                .dataHora(LocalDateTime.now())
                .usuario(UsuarioEntity.builder()
                        .id(UUID.randomUUID()).nome("Rafael")
                        .endereco(EnderecoEntity.builder().build()).build())
                .build();

        var item2 = ItemPedido.builder()
                .nome("Hamburger")
                .preco(BigDecimal.TEN)
                .dataHora(LocalDateTime.now())
                .usuario(UsuarioEntity.builder()
                        .id(UUID.randomUUID()).nome("Rafael")
                        .endereco(EnderecoEntity.builder().build()).build())
                .build();

        return Pedido.builder()
                .acrescimo(BigDecimal.TEN)
                .valorEntrega(BigDecimal.TEN)
                .itens(Arrays.asList(item1, item2))
                .build();
    }
}
