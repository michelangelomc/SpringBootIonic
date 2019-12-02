package com.br.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.cursomc.domain.Categoria;
import com.br.cursomc.domain.Cidade;
import com.br.cursomc.domain.Cliente;
import com.br.cursomc.domain.Endereco;
import com.br.cursomc.domain.Estado;
import com.br.cursomc.domain.Produto;
import com.br.cursomc.domain.enums.TipoCliente;
import com.br.cursomc.repositories.CategoriaRepository;
import com.br.cursomc.repositories.CidadeRepository;
import com.br.cursomc.repositories.ClienteRepository;
import com.br.cursomc.repositories.EnderecoRepository;
import com.br.cursomc.repositories.EstadoRepository;
import com.br.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria categoriaI = new Categoria(null, "Infomatica");
		Categoria categoriaE = new Categoria(null, "Escritório");
		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);

		categoriaI.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		categoriaE.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(categoriaI));
		p2.getCategorias().addAll(Arrays.asList(categoriaI, categoriaE));
		p3.getCategorias().addAll(Arrays.asList(categoriaI));

		categoriaRepository.saveAll(Arrays.asList(categoriaI, categoriaE));
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

		Cliente cl1 = new Cliente(null, "Maria Silva", "M@g", "63655558811", TipoCliente.PESSOAFISICA);
		cl1.getTelefone().addAll(Arrays.asList("11111111", "111122223"));

		Endereco ed1 = new Endereco(null, "Rua a", "300", "Apto 303", "Jardim", "3888889", cl1, c1);
		Endereco ed2 = new Endereco(null, "Av mAtos", "555", "Apto 77", "AAA", "dddd88", cl1, c2);

		cl1.getEndereco().addAll(Arrays.asList(ed1, ed2));
		
		clienteRepository.saveAll(Arrays.asList(cl1));
		enderecoRepository.saveAll(Arrays.asList(ed1, ed2));
		
	}
}
