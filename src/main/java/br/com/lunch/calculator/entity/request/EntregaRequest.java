package br.com.lunch.calculator.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntregaRequest {
    @ApiModelProperty(position = 1, example = "2731f09c49cb4b0ebe4529083769afcat")
    @NotEmpty
    private String enderecoEntregaId;
    @ApiModelProperty(position = 2, example = "8.00")
    @Digits(integer = 4, fraction = 2)
    @NotEmpty
    private BigDecimal valorEntrega;
}
