package br.com.lunch.calculator.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EnderecoRequest {
    @ApiModelProperty(position = 1, example = "av das rosas")
    @NotEmpty
    private String logradouro;
    @ApiModelProperty(position = 2, example = "104A")
    @NotEmpty
    private String numero;
}
