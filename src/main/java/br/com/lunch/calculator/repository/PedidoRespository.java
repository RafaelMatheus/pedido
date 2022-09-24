package br.com.lunch.calculator.repository;

import br.com.lunch.calculator.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRespository extends JpaRepository<PedidoEntity, String> {
    Optional<PedidoEntity> findByCodigoPagamento(final String codigoPagamento);
}
