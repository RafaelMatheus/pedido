package br.com.lunch.calculator.entity;

import br.com.lunch.calculator.entity.request.enums.FormaPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pedido")
@SuperBuilder
public class PedidoEntity extends BaseEntity {
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<ItemPedido> itens;
    @OneToOne
    private EnderecoEntity enderecoEntrega;
    private BigDecimal valorEntrega;
    private BigDecimal desconto;
    private String linkPagamento;
    @Enumerated(EnumType.STRING)
    private FormaPagamentoEnum formaPagamento;
    private String codigoPedido;
}
