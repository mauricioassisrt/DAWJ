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

import br.com.minhaloja.minhaloja.model.Pedido;
import br.com.minhaloja.minhaloja.model.Pessoa;
import br.com.minhaloja.minhaloja.repositorio.PedidoRepositorio;
import br.com.minhaloja.minhaloja.repositorio.PessoaRepositorio;
import br.com.minhaloja.minhaloja.repositorio.ProdutoRepositorio;


@Controller
public class PedidoController {
	@Autowired
	private PedidoRepositorio repositorioPedido;
	private PessoaRepositorio repositorioPessoa;
	private ProdutoRepositorio repositorioProdutos;
	
	@GetMapping("pedidos/cadastrar")
	public ModelAndView cadastrar(Pedido pedido) {
		ModelAndView mv = new ModelAndView("pedidos/formulario");
		mv.addObject("listaPessoas", repositorioPessoa.findAll());
		mv.addObject("listaProdutos", repositorioProdutos.findAll());
		mv.addObject("pedidos", pedido);
		return mv;
	}
	@PostMapping("pedidos/salvar")
	public ModelAndView salvar(@Valid Pedido pedidos, BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(pedidos);
		}
		repositorioPedido.saveAndFlush(pedidos);
		return cadastrar(new Pedido());
	}
	@GetMapping("pedidos")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("pedidos/lista");
		mv.addObject("listaPedidos", repositorioPedido.findAll());
		return mv;
	}
	@GetMapping("pedidos/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		
		Optional<Pedido> objetoPedido = repositorioPedido.findById(id);
		
		return cadastrar(objetoPedido.get());
		
	}
	@GetMapping("pedidos/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		
		Optional<Pedido> objetoPessoa = repositorioPedido.findById(id);
		repositorioPedido.delete(objetoPessoa.get());
		
		return listar();
		
	}
}
