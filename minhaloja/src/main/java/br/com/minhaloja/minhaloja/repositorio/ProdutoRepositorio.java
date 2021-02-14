package br.com.minhaloja.minhaloja.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.minhaloja.minhaloja.model.Pessoa;
import br.com.minhaloja.minhaloja.model.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

}
