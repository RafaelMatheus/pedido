package helper;

import br.com.lunch.calculator.entity.PedidoEntity;
import br.com.lunch.calculator.helper.GerarCodigoProvider;
import br.com.lunch.calculator.helper.impl.PicPayGerarCodigoProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PicPayGerarCodigoProviderTest {

    @Test
    void deveRetornarCodigoGeradoParaPedido() {
        final GerarCodigoProvider provider = new PicPayGerarCodigoProvider();

        assertNotNull(provider.gerarCodigo(PedidoEntity.builder().build()));
    }
}
