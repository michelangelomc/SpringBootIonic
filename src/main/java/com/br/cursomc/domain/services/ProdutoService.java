package com.br.cursomc.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.br.cursomc.domain.Categoria;
import com.br.cursomc.domain.Produto;
import com.br.cursomc.domain.services.exeptions.ObjectNotFoundException;
import com.br.cursomc.repositories.CategoriaRepository;
import com.br.cursomc.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	private final String msgErro = "Objeto não foi encontrado id: ";

	public Optional<Produto> getProdutoById(Integer id) {
		Optional<Produto> cliente = produtoRepository.findById(id);

		cliente.orElseThrow(() -> new ObjectNotFoundException(msgErro + id, Produto.class.getName()));

		return cliente;
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.search(nome, categorias, pageRequest);
	}
}