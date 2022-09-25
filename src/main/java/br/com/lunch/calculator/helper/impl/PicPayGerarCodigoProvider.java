package br.com.lunch.calculator.helper.impl;

import br.com.lunch.calculator.entity.ItemPedido;
import br.com.lunch.calculator.entity.PedidoEntity;
import br.com.lunch.calculator.entity.request.enums.FormaPagamentoEnum;
import br.com.lunch.calculator.helper.GerarCodigoProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

import static br.com.lunch.calculator.entity.request.enums.FormaPagamentoEnum.PICPAY;

@Component
@Slf4j
public class PicPayGerarCodigoProvider implements GerarCodigoProvider {

    @Override
    public String gerarCodigo(final PedidoEntity pedido) {

        return UUID.randomUUID().toString();
    }

    @Override
    public FormaPagamentoEnum getEmpresa() {
        return PICPAY;
    }
}
