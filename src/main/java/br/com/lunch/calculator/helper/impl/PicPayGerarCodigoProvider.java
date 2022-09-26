package br.com.lunch.calculator.helper.impl;

import br.com.lunch.calculator.entity.PedidoEntity;
import br.com.lunch.calculator.entity.request.enums.FormaPagamentoEnum;
import br.com.lunch.calculator.helper.GerarCodigoProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static br.com.lunch.calculator.entity.request.enums.FormaPagamentoEnum.PICPAY;

@Component
@Slf4j
public class PicPayGerarCodigoProvider implements GerarCodigoProvider {

    @Override
    public String gerarLinkPagamento(final PedidoEntity pedido) {
        log.info("Gerando link de pagamento da empresa '{}'", this.getEmpresa());
        final String codigoGerado = UUID.randomUUID().toString();
        log.info("link gerado com sucesso: '{}'", codigoGerado);
        return "https://picpay/".concat(codigoGerado);
    }

    @Override
    public FormaPagamentoEnum getEmpresa() {
        return PICPAY;
    }
}
