package br.com.lunch.calculator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "endereco")
@SuperBuilder
public class EnderecoEntity extends BaseEntity {
    private String logradouro;
    private String numero;
    @OneToOne(mappedBy = "endereco")
    private UsuarioEntity usuario;
}
