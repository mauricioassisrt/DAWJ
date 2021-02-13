package br.com.minhaloja.minhaloja.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="pedido")
public class Pedido implements Serializable {
	public Pedido() {
		super();
	}
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date dataPedido;
	@ManyToOne
	private Pessoa objetoPessoa;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Pessoa getObjetoPessoa() {
		return objetoPessoa;
	}
	public void setObjetoPessoa(Pessoa objetoPessoa) {
		this.objetoPessoa = objetoPessoa;
	}
	
}
