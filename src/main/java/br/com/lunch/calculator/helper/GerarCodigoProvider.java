package br.com.lunch.calculator.helper;

import br.com.lunch.calculator.entity.PedidoEntity;
import br.com.lunch.calculator.entity.request.enums.FormaPagamentoEnum;

public interface GerarCodigoProvider {
    String gerarCodigo(final PedidoEntity pedido);

    FormaPagamentoEnum getEmpresa();
}
