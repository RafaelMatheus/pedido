package br.com.lunch.calculator.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnderecoRequest {
    @ApiModelProperty(position = 1, example = "av das rosas")
    private String logradouro;
    @ApiModelProperty(position = 2, example = "104A")
    private String numero;
}
