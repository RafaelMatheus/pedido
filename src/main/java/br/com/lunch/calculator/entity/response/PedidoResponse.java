package br.com.lunch.calculator.entity.response;

import br.com.lunch.calculator.entity.request.ItemPedidoRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponse {
    private String enderecoEntregaId;
    private BigDecimal valorEntrega;
    private BigDecimal acrescimo;
    private List<ItemPedidoRequest> itens;
    private String linkPagamento;
    private String codigoPagamento;
}
