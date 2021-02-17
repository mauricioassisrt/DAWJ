package br.com.minhaloja.minhaloja.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import br.com.minhaloja.minhaloja.repositorio.ItensRepositorio;
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
	@Autowired
	private ItensRepositorio repositorioItens;

	private List<ItensPedido> itensLista = new ArrayList<>();
	 public Double quantidadeItens =0., valorTotalPedido=0.;
	
	@GetMapping("pedidos/cadastrar")
	public ModelAndView cadastrar(Pedido pedidos, ItensPedido itensPedido, Produto produtos) {
		
		ModelAndView mv = new ModelAndView("pedidos/formulario");
		mv.addObject("listaProdutos", repositorioProdutos.findAll());
		mv.addObject("listaPessoas", repositorioPessoa.findAll());
		mv.addObject("itensLista", this.itensLista);
		mv.addObject("contador",quantidadeItens);
		mv.addObject("valorTotalPedido", valorTotalPedido);
		mv.addObject("itens", itensPedido);
		mv.addObject("pedidos", pedidos);
		mv.addObject("produtos", produtos);
	
		return mv;
	}

	@PostMapping("pedidos/salvar")
	public ModelAndView salvar(String acao, Pedido pedidos, ItensPedido itensPedido, Produto produtos) {
		ModelAndView mv = new ModelAndView("pedidos/formulario");
		if (acao.equals("itens")) {
			System.out.println();
			
			if(itensLista.size()==0) {
				valorTotalPedido =itensPedido.getQuantidade()* itensPedido.getObjetoProduto().getValorVenda().doubleValue() ;
				quantidadeItens+=quantidadeItens+itensPedido.getQuantidade();
				System.out.print("LINHA 72");
				this.itensLista.add(itensPedido);
				
			}else {
				for (ItensPedido item : itensLista) {
					if(item.getObjetoProduto().getId().equals(itensPedido.getObjetoProduto().getId())) {
						//Quantidade de produtos
						quantidadeItens = (double) (item.getQuantidade()+itensPedido.getQuantidade());
						
						break;
					}else {
						valorTotalPedido =itensPedido.getQuantidade()* itensPedido.getObjetoProduto().getValorVenda().doubleValue() ;
						quantidadeItens+=quantidadeItens+itensPedido.getQuantidade();
						System.out.print("LINHA 85 " +item.getObjetoProduto().getId());
						
						this.itensLista.add(itensPedido);
						break;
					}
					
				}
				
			}
			
			
		} else if (acao.equals("salvar")) {
			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date date = new Date();
				String data = dateFormat.format(date);

				Date dateA = dateFormat.parse(data);
				pedidos.setDataPedido(dateA);
				repositorioPedido.saveAndFlush(pedidos);

				for (ItensPedido itens : itensLista) {
					itens.setObjetoPedido(pedidos);
					repositorioItens.saveAndFlush(itens);
				}
				itensLista = new ArrayList<>();
				return listar();

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// repositorioPedido.saveAndFlush(pedidos);
		return cadastrar(pedidos, itensPedido, produtos);
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
