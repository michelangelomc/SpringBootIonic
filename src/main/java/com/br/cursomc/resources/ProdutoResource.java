package com.br.cursomc.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.cursomc.domain.Produto;
import com.br.cursomc.domain.services.ProdutoService;
import com.br.cursomc.dto.CategoriaDTO;
import com.br.cursomc.dto.ProdutoDTO;
import com.br.cursomc.resources.utis.TreatmentUrl;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;

//	@RequestMapping(method = RequestMethod.GET)
//	public String getMessage() {
//		return "OK";
//	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Produto>> findById(@PathVariable Integer id) {
		Optional<Produto> produto = produtoService.getProdutoById(id);
		return ResponseEntity.ok().body(produto);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "0") String nome,
			@RequestParam(value = "categorias", defaultValue = "0") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		String nomeDecode = TreatmentUrl.decodeParam(nome);
		List<Integer> ids = TreatmentUrl.decondeIntForList(categorias);
		Page<Produto> produtos = produtoService.search(nomeDecode, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> lsitDto = produtos.map(obj -> new ProdutoDTO(obj));
		
		return ResponseEntity.ok().body(lsitDto);
	}
}
