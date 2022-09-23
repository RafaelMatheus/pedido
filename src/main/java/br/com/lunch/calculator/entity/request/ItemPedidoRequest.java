package br.com.lunch.calculator.entity.request;

import br.com.lunch.calculator.entity.PedidoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemPedidoRequest {
    private String nome;
    private BigDecimal preco;
    private PedidoEntity pedido;
    private UUID usuarioId;

}
