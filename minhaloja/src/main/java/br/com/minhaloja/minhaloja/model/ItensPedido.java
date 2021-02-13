package br.com.minhaloja.minhaloja.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="itens_pedido")
public class ItensPedido implements Serializable {
	public ItensPedido() {
		super();
	}
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Integer quantidade;
	@ManyToOne
	private Pedido objetoPedido;
	@ManyToOne
	private  Produto objetoProduto;
	
	
}
