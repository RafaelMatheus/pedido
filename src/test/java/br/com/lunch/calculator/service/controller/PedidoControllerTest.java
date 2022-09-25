package br.com.lunch.calculator.service.controller;

import br.com.lunch.calculator.config.WebMvcConfiguration;
import br.com.lunch.calculator.controller.PedidoController;
import br.com.lunch.calculator.entity.request.PedidoRequest;
import br.com.lunch.calculator.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {
    private static final String PATH = "/pedidos";

    @Autowired
    private MockMvc mvc;
    @MockBean
    private PedidoService service;
    @MockBean
    private WebMvcConfiguration mvcConfig;

    public void deveCriarPedidoComUmUsuario(){

    }

    public void deveCriarPedidoComMaisDeUmUsuarioETaxas(){

    }

    public void deveCriarPedidoComUsuarioETaxas(){

    }

    public void deveCriarPedidoComUmUsuarioETaxas(){

    }

    public void deveValidarCamposObrigatorios(){
        //itensPedido
        // forma de pagamento
    }
}
