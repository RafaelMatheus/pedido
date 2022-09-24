package br.com.lunch.calculator.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class ItemPedidoRequest {
    @ApiModelProperty(position = 1, example = "rafael")
    private String nome;
    @ApiModelProperty(position = 2, example = "25.50")
    private BigDecimal preco;
    @ApiModelProperty(position = 3, example = "2731f09c49cb4b0ebe4529083769afca")
    private String usuarioId;

}
