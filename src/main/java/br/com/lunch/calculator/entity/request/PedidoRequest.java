package br.com.lunch.calculator.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {
    private List<ItemPedidoRequest> itens;
    private UUID enderecoEntregaId;
    private BigDecimal valorEntrega;
    private BigDecimal acrescimo;
}
