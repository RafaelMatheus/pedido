package br.com.lunch.calculator.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class ItemPedidoRequest {
    @ApiModelProperty(position = 1, example = "rafael")
    @NotEmpty
    private String nome;
    @ApiModelProperty(position = 2, example = "25.50")
    @NotNull
    @Digits(integer = 4, fraction = 2)
    private BigDecimal preco;
    @ApiModelProperty(position = 3, example = "2731f09c49cb4b0ebe4529083769afca")
    @NotEmpty
    private String usuarioId;

}
