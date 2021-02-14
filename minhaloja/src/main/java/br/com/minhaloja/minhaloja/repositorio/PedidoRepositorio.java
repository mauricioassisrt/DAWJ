package br.com.minhaloja.minhaloja.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.minhaloja.minhaloja.model.Pedido;
public interface PedidoRepositorio extends JpaRepository<Pedido, Long>{

}
