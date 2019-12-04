package com.br.cursomc.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cursomc.domain.Cliente;
import com.br.cursomc.domain.services.exeptions.ObjectNotFoundException;
import com.br.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	private final String msgErro = "Objeto não foi encontrado id: ";

	public Optional<Cliente> getClienteById(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

		cliente.orElseThrow(() -> new ObjectNotFoundException(msgErro + id, Cliente.class.getName()));

		return cliente;
	}
}