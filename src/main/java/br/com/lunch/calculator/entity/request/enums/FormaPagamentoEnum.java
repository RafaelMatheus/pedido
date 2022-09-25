package br.com.lunch.calculator.entity.request.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormaPagamentoEnum {
    PICPAY("PICPAY"),
    PAYPAL("PAYPAL");

    private String nome;
}
