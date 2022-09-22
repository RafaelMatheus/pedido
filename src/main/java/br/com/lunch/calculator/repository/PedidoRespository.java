package br.com.lunch.calculator.repository;

import br.com.lunch.calculator.entity.Pedido;
import br.com.lunch.calculator.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRespository extends JpaRepository<Pedido, UUID> {
}
