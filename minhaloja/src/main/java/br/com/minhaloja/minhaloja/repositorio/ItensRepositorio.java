package br.com.minhaloja.minhaloja.repositorio;



import org.springframework.data.jpa.repository.JpaRepository;

import br.com.minhaloja.minhaloja.model.ItensPedido;
import br.com.minhaloja.minhaloja.model.Pessoa;

public interface ItensRepositorio extends JpaRepository<ItensPedido, Long>{

}
