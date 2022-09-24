package br.com.lunch.calculator.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoResponse {
    private String id;
    private String logradouro;
    private String numero;
}
