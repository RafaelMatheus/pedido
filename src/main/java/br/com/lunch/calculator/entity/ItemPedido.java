package br.com.lunch.calculator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class ItemPedido extends BaseEntity {
    private String nome;
    private BigDecimal preco;
    @ManyToOne
    private PedidoEntity pedido;
    @OneToOne
    private UsuarioEntity usuario;

}
