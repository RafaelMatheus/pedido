package br.com.lunch.calculator.controller;

import br.com.lunch.calculator.entity.request.PedidoRequest;
import br.com.lunch.calculator.entity.response.CalculaPedidoResponse;
import br.com.lunch.calculator.entity.response.PedidoResponse;
import br.com.lunch.calculator.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("pedidos")
@RestController
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService service;

    @PostMapping
    public ResponseEntity<PedidoResponse> createPedido(final @RequestBody PedidoRequest pedido) {
        service.criarPedido(pedido);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{pedidoId}/total")
    public ResponseEntity<CalculaPedidoResponse> getTotalPedido(@PathVariable final UUID pedidoId) throws Exception {
        return ResponseEntity.ok(this.service.calcularValorPedido(pedidoId));
    }
}
