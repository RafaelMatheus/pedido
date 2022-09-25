package br.com.lunch.calculator.entity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoResponse {
    private String enderecoEntregaId;
    private BigDecimal valorEntrega;
    private BigDecimal desconto;
    private List<ItemPedidoResponse> itens;
    private String linkPagamento;
    private String codigoPagamento;
}
