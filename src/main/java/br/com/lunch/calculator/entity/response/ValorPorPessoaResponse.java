package br.com.lunch.calculator.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValorPorPessoaResponse {
    private UsuarioResponse usuarioResponse;
    private BigDecimal valor;
    private BigDecimal percentualTotal;
}
