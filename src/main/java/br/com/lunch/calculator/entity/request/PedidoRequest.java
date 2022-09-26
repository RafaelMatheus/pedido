package br.com.lunch.calculator.entity.request;

import br.com.lunch.calculator.entity.request.enums.FormaPagamentoEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoRequest {
    @ApiModelProperty(position = 1, example = "10.00")
    @Digits(integer = 4, fraction = 2)
    private BigDecimal desconto;
    @ApiModelProperty(position = 2, example = "10.00")
    @Digits(integer = 4, fraction = 2)
    private BigDecimal acrescimoReal;
    @ApiModelProperty(position = 3, example = "10")
    @Digits(integer = 4, fraction = 2)
    private BigDecimal percentualAcrescimo;
    @ApiModelProperty(position = 4)
    @NotNull
    private List<ItemPedidoRequest> itens;
    @ApiModelProperty(position = 5, example = "PICPAY/PAYPAL")
    @NotNull
    private FormaPagamentoEnum formaPagamento;
    private EntregaRequest entrega;
}
