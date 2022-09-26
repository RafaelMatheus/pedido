package br.com.lunch.calculator.service.controller;

import br.com.lunch.calculator.config.WebMvcConfiguration;
import br.com.lunch.calculator.controller.EnderecoController;
import br.com.lunch.calculator.entity.request.EnderecoRequest;
import br.com.lunch.calculator.entity.response.EnderecoResponse;
import br.com.lunch.calculator.service.EnderecoService;
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
@WebMvcTest(EnderecoController.class)
public class EnderecoControllerTest {
    private static final String PATH = "/enderecos";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EnderecoService service;

    @MockBean
    private WebMvcConfiguration mvcConfig;

    @Test
    public void postSucesso() throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("traceId", "REQUEST_TRACE_ID");

        final EnderecoRequest request = EnderecoRequest.builder().numero("140a").logradouro("Teste").build();

        when(this.service.criarEndereco(any())).thenReturn(EnderecoResponse.builder().logradouro("Teste").numero("140a").build());

        mvc
                .perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .headers(httpHeaders)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numero").value("140a"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.logradouro").value("Teste"));
    }

    @ParameterizedTest
    @MethodSource("obter")
    public void postErroBadRequest(final EnderecoRequest request) throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("traceId", "REQUEST_TRACE_ID");

        mvc
                .perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .headers(httpHeaders)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    private static Stream<EnderecoRequest> obter() {
        return Stream.of(EnderecoRequest.builder().build(),
                EnderecoRequest.builder().logradouro("").build(),
                EnderecoRequest.builder().logradouro("logradouro").build(),
                EnderecoRequest.builder().numero("").build(),
                EnderecoRequest.builder().numero("140B").build());
    }

}
