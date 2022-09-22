package br.com.lunch.calculator.helper.impl;

import br.com.lunch.calculator.entity.ItemPedido;
import br.com.lunch.calculator.helper.GerarCodigoProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class PicPayGerarCodigoProvider implements GerarCodigoProvider {

    private static final String PICPAY = "PICPAY";

    @Override
    public String gerarCodigo(final ItemPedido itemPedido) {
        log.info("Generating a picpay payment code for pedido '{}'");
        return UUID.randomUUID().toString();
    }

    @Override
    public String getEmpresa() {
        return PICPAY;
    }
}
