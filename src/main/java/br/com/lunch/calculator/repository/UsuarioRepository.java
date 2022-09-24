package br.com.lunch.calculator.repository;

import br.com.lunch.calculator.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {
}
