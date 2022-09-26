package br.com.lunch.calculator.controller;

import br.com.lunch.calculator.entity.request.PedidoRequest;
import br.com.lunch.calculator.entity.response.CalculaPedidoResponse;
import br.com.lunch.calculator.entity.response.PedidoResponse;
import br.com.lunch.calculator.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("pedidos")
@RestController
@RequiredArgsConstructor
@Slf4j
public class PedidoController {
    private final PedidoService service;

    @PostMapping
    public ResponseEntity<PedidoResponse> createPedido(final @RequestBody @Valid PedidoRequest pedido) {
        return ResponseEntity.ok(service.criarPedido(pedido));
    }

    @GetMapping("{codigoPagamento}/total")
    public ResponseEntity<CalculaPedidoResponse> getTotalPedido(@PathVariable final String codigoPagamento) {
        return ResponseEntity.ok(this.service.calcularValorPedido(codigoPagamento));
    }
}
