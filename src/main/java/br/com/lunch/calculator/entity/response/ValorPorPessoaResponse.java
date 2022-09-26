package br.com.lunch.calculator.entity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValorPorPessoaResponse {
    private String usuario;
    private BigDecimal valor;
    private BigDecimal valorDesconto;
    private BigDecimal percentualTotal;
}
