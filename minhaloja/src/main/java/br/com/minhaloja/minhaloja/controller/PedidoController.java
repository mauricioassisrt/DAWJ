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

import br.com.minhaloja.minhaloja.model.ItensPedido;
import br.com.minhaloja.minhaloja.model.Pedido;
import br.com.minhaloja.minhaloja.model.Pessoa;
import br.com.minhaloja.minhaloja.model.Produto;
import br.com.minhaloja.minhaloja.repositorio.PedidoRepositorio;
import br.com.minhaloja.minhaloja.repositorio.PessoaRepositorio;
import br.com.minhaloja.minhaloja.repositorio.ProdutoRepositorio;


@Controller
public class PedidoController {
	
	@Autowired
	private PedidoRepositorio repositorioPedido;
	@Autowired
	private PessoaRepositorio repositorioPessoa;
	@Autowired
	private ProdutoRepositorio repositorioProdutos;
	
	@GetMapping("pedidos/cadastrar")
	public ModelAndView cadastrar(Pedido pedidos, ItensPedido itensPedido, Produto produtos) {
		ModelAndView mv = new ModelAndView("pedidos/formulario");
		mv.addObject("listaProdutos", repositorioProdutos.findAll());
		mv.addObject("listaPessoas", repositorioPessoa.findAll());
		
		mv.addObject("itens", itensPedido );
		
		return mv;
	}
	@PostMapping("pedidos/salvar")
	public ModelAndView salvar(@Valid Pedido pedidos, ItensPedido itens, Produto produtos, BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(pedidos, itens, produtos);
		}
		
		System.out.println(itens.getObjetoPedido().getObjetoPessoa().getId());
		//repositorioPedido.saveAndFlush(pedidos);
		return cadastrar(new Pedido(), new ItensPedido(), new Produto());
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
		
		return cadastrar(objetoPedido.get(), null, null);
		
	}
	@GetMapping("pedidos/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		
		Optional<Pedido> objetoPessoa = repositorioPedido.findById(id);
		repositorioPedido.delete(objetoPessoa.get());
		
		return listar();
		
	}
	
	
}
