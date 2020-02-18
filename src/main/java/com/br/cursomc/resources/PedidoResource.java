package com.br.cursomc.resources;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.cursomc.domain.Categoria;
import com.br.cursomc.domain.Pedido;
import com.br.cursomc.domain.services.PedidoService;
import com.br.cursomc.dto.CategoriaDTO;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;

	@RequestMapping(method = RequestMethod.GET)
	public String getMessage() {
		return "OK";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Pedido>> findById(@PathVariable Integer id) {
		Optional<Pedido> pedido = pedidoService.getPedidoById(id);
		return ResponseEntity.ok().body(pedido);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CategoriaDTO> insert(@Valid @RequestBody Pedido pedido) {
		pedido = pedidoService.insert(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
}
