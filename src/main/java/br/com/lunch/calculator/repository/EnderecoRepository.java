package br.com.lunch.calculator.repository;

import br.com.lunch.calculator.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, String> {
}
