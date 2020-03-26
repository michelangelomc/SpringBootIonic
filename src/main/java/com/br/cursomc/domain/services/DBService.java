package com.br.cursomc.domain.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cursomc.domain.Categoria;
import com.br.cursomc.domain.Cidade;
import com.br.cursomc.domain.Cliente;
import com.br.cursomc.domain.Endereco;
import com.br.cursomc.domain.Estado;
import com.br.cursomc.domain.ItemPedido;
import com.br.cursomc.domain.Pagamento;
import com.br.cursomc.domain.PagamentoBoleto;
import com.br.cursomc.domain.PagamentoCartao;
import com.br.cursomc.domain.Pedido;
import com.br.cursomc.domain.Produto;
import com.br.cursomc.domain.enums.EstadoPagamento;
import com.br.cursomc.domain.enums.TipoCliente;
import com.br.cursomc.repositories.CategoriaRepository;
import com.br.cursomc.repositories.CidadeRepository;
import com.br.cursomc.repositories.ClienteRepository;
import com.br.cursomc.repositories.EnderecoRepository;
import com.br.cursomc.repositories.EstadoRepository;
import com.br.cursomc.repositories.ItemPedidoRepositoy;
import com.br.cursomc.repositories.PagamentoRepository;
import com.br.cursomc.repositories.PedidoRepository;
import com.br.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ItemPedidoRepositoy itemPedidoRepository;

	public void instateateDatabase() throws ParseException {

		Categoria categoriaI = new Categoria(null, "Infomatica");
		Categoria categoriaE = new Categoria(null, "Escritório");
		Categoria categoriaF = new Categoria(null, "Eletronica");
		Categoria categoriaG = new Categoria(null, "Jardinagem");
		Categoria categoriaH = new Categoria(null, "Armas");

		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);

		categoriaI.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		categoriaE.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(categoriaI));
		p2.getCategorias().addAll(Arrays.asList(categoriaI, categoriaE));
		p3.getCategorias().addAll(Arrays.asList(categoriaI));

		categoriaRepository.saveAll(Arrays.asList(categoriaI, categoriaE, categoriaF, categoriaG, categoriaH));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidade().addAll(Arrays.asList(c1));
		est2.getCidade().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cl1 = new Cliente(null, "Maria Silva", "mickelchaves@hotmail.com", "63655558811", TipoCliente.PESSOAFISICA);
		cl1.getTelefone().addAll(Arrays.asList("11111111", "111122223"));

		Endereco ed1 = new Endereco(null, "Rua a", "300", "Apto 303", "Jardim", "3888889", cl1, c1);
		Endereco ed2 = new Endereco(null, "Av mAtos", "555", "Apto 77", "AAA", "dddd88", cl1, c2);

		cl1.getEndereco().addAll(Arrays.asList(ed1, ed2));

		clienteRepository.saveAll(Arrays.asList(cl1));
		enderecoRepository.saveAll(Arrays.asList(ed1, ed2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Pedido pd1 = new Pedido(null, sdf.parse("01/01/2058 10:32"), cl1, ed1);
		Pedido pd2 = new Pedido(null, sdf.parse("01/01/2056 10:32"), cl1, ed2);

		Pagamento pg1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, pd1, 6);
		pd1.setPagamento(pg1);

		Pagamento pg2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, pd2, sdf.parse("01/12/2036 10:32"), null);
		pd2.setPagamento(pg2);

		cl1.getPedido().addAll(Arrays.asList(pd1, pd2));

		pedidoRepository.saveAll(Arrays.asList(pd1, pd2));
		pagamentoRepository.saveAll(Arrays.asList(pg1, pg2));

		ItemPedido ip1 = new ItemPedido(pd1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(pd1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(pd2, p2, 0.00, 3, 800.00);

		p1.getItens().addAll(Arrays.asList(ip1, ip2));
		p2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip2));
		p3.getItens().addAll(Arrays.asList(ip3));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
