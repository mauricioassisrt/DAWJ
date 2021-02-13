package br.com.minhaloja.minhaloja.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.minhaloja.minhaloja.model.Pessoa;

public interface FuncionarioRepositorio extends JpaRepository<Pessoa, Long> {

}
