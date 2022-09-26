package br.com.lunch.calculator.entity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalculaPedidoResponse {
    private BigDecimal valorTotalCompra;
    private String codigoPedido;
    private String linkPagamento;
    private List<ValorPorPessoaResponse> valorTotalPorPessoa;
}
