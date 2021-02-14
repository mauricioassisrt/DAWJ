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

import br.com.minhaloja.minhaloja.model.Produto;
import br.com.minhaloja.minhaloja.repositorio.PessoaRepositorio;
import br.com.minhaloja.minhaloja.repositorio.ProdutoRepositorio;

@Controller
public class ProdutoController {
	@Autowired
	private ProdutoRepositorio repositorioproduto;
	
	@GetMapping("produtos/cadastrar")
	public ModelAndView cadastrar(Produto produto) {
		ModelAndView mv = new ModelAndView("produtos/formulario");
		mv.addObject("produto", produto);
		return mv;
	}
	@PostMapping("produtos/salvar")
	public ModelAndView salvar(@Valid Produto objeto, BindingResult result) {
		if(result.hasErrors()) {
			
			return cadastrar(objeto);
			
		}
		
		repositorioproduto.saveAndFlush(objeto);
		return cadastrar(new Produto());
	}
	@GetMapping("produtos")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("produtos/lista");
		mv.addObject("listaProdutos", repositorioproduto.findAll());
		return mv;
	}
	@GetMapping("produtos/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		
		Optional<Produto> objetoproduto = repositorioproduto.findById(id);
		
		return cadastrar(objetoproduto.get());
		
	}
	@GetMapping("produtos/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		
		Optional<Produto> objetoproduto = repositorioproduto.findById(id);
		repositorioproduto.delete(objetoproduto.get());
		
		return listar();
		
	}

}
