package com.br.cursomc.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cursomc.domain.Pedido;
import com.br.cursomc.domain.services.exeptions.ObjectNotFoundException;
import com.br.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	private final String msgErro = "Objeto não foi encontrado id: ";

	public Optional<Pedido> getPedidoById(Integer id) {
		Optional<Pedido> cliente = pedidoRepository.findById(id);

		cliente.orElseThrow(() -> new ObjectNotFoundException(msgErro + id, Pedido.class.getName()));

		return cliente;
	}
}