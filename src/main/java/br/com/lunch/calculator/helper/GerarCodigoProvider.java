package br.com.lunch.calculator.helper;

import br.com.lunch.calculator.entity.ItemPedido;

public interface GerarCodigoProvider {
    String gerarCodigo(final ItemPedido itemPedido);
}
