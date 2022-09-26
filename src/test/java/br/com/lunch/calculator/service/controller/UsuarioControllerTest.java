package br.com.lunch.calculator.service.controller;

import br.com.lunch.calculator.config.WebMvcConfiguration;
import br.com.lunch.calculator.controller.UsuarioController;
import br.com.lunch.calculator.entity.request.UsuarioRequest;
import br.com.lunch.calculator.entity.response.UsuarioResponse;
import br.com.lunch.calculator.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {
    private static final String PATH = "/usuarios";

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UsuarioService service;

    @MockBean
    private WebMvcConfiguration mvcConfig;

    @Test
    public void postSucesso() throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("traceId", "REQUEST_TRACE_ID");

        final UsuarioRequest request = UsuarioRequest.builder().nome("rafael").build();

        when(this.service.criarUsuario(any())).thenReturn(UsuarioResponse.builder().nome("rafael").build());

        mvc
                .perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .headers(httpHeaders)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("rafael"));
    }

    @ParameterizedTest
    @MethodSource("obter")
    public void postErroBadRequest(final UsuarioRequest request) throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("traceId", "REQUEST_TRACE_ID");

        mvc
                .perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .headers(httpHeaders)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    private static Stream<UsuarioRequest> obter() {
        return Stream.of(UsuarioRequest.builder().build(),
                UsuarioRequest.builder().nome("").build());
    }

}
