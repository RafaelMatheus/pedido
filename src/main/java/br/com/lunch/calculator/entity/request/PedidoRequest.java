package br.com.lunch.calculator.entity.request;

import br.com.lunch.calculator.entity.request.enums.FormaPagamentoEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {
    @ApiModelProperty(position = 1, example = "2731f09c49cb4b0ebe4529083769afcat")
    @NotEmpty
    private String enderecoEntregaId;
    @ApiModelProperty(position = 2, example = "8.00")
    @Digits(integer = 4, fraction = 2)
    private BigDecimal valorEntrega;
    @ApiModelProperty(position = 3, example = "10.00")
    @Digits(integer = 4, fraction = 2)
    private BigDecimal desconto;
    @ApiModelProperty(position = 4)
    private List<ItemPedidoRequest> itens;
    @ApiModelProperty(position = 5, example = "PICPAY/PAYPAL")
    private FormaPagamentoEnum formaPagamento;
}
