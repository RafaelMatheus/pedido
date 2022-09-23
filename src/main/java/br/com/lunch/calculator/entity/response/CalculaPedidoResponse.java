package br.com.lunch.calculator.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CalculaPedidoResponse {
    private BigDecimal valorTotalCompra;
    private List<ValorPorPessoaResponse> valorTotalPorPessoa;
}
