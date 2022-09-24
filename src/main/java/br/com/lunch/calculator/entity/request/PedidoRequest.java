package br.com.lunch.calculator.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {
    @ApiModelProperty(position = 1, example = "2731f09c49cb4b0ebe4529083769afcat")
    private String enderecoEntregaId;
    @ApiModelProperty(position = 2, example = "8.00")
    private BigDecimal valorEntrega;
    @ApiModelProperty(position = 3, example = "10.00")
    private BigDecimal acrescimo;
    @ApiModelProperty(position = 4)
    private List<ItemPedidoRequest> itens;

}
