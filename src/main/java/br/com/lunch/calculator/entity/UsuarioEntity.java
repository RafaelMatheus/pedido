package br.com.lunch.calculator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UsuarioEntity extends BaseEntity {

    private String nome;
    @OneToOne
    private EnderecoEntity endereco;
}
