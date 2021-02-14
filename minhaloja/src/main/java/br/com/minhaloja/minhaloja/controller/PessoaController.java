package br.com.minhaloja.minhaloja.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import br.com.minhaloja.minhaloja.model.Pessoa;
import br.com.minhaloja.minhaloja.repositorio.PessoaRepositorio;

@Controller
public class PessoaController {
	@Autowired
	private PessoaRepositorio repositorioPessoa;
	
	@GetMapping("pessoas/cadastrar")
	public ModelAndView cadastrar(Pessoa pessoa) {
		ModelAndView mv = new ModelAndView("pessoas/formulario");
		mv.addObject("pessoa", pessoa);
		return mv;
	}
	@PostMapping("pessoas/salvar")
	public ModelAndView salvar(@Valid Pessoa pessoa, BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(pessoa);
		}
		repositorioPessoa.saveAndFlush(pessoa);
		return cadastrar(new Pessoa());
	}
	@GetMapping("pessoas")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("pessoas/lista");
		mv.addObject("listaPessoas", repositorioPessoa.findAll());
		return mv;
	}
	@GetMapping("pessoas/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		
		Optional<Pessoa> objetoPessoa = repositorioPessoa.findById(id);
		
		return cadastrar(objetoPessoa.get());
		
	}
	@GetMapping("pessoas/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		
		Optional<Pessoa> objetoPessoa = repositorioPessoa.findById(id);
		repositorioPessoa.delete(objetoPessoa.get());
		
		return listar();
		
	}
}
