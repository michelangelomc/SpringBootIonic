package com.br.cursomc.domain.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.br.cursomc.domain.Cliente;
import com.br.cursomc.domain.services.exeptions.DataIntegrityException;
import com.br.cursomc.domain.services.exeptions.ObjectNotFoundException;
import com.br.cursomc.dto.ClienteDTO;
import com.br.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	private final String msgErro = "Objeto não foi encontrado id: ";
	private final String msgErroIntegrity = "Nao é possivel excluir um cliente, pois possui entidades relacionadas.";

	public Optional<Cliente> getClienteById(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

		cliente.orElseThrow(() -> new ObjectNotFoundException(msgErro + id, Cliente.class.getName()));

		return cliente;
	}

	public Cliente update(Cliente cliente) {
		Optional<Cliente> clienteRecover = getClienteById(cliente.getId());
		updateData(clienteRecover, cliente);
		return clienteRepository.save(clienteRecover.get());
	}

	private void updateData(Optional<Cliente> clienteRecover, Cliente cliente) {
		if (clienteRecover.isPresent()) {
			clienteRecover.get().setNome(cliente.getNome());
			clienteRecover.get().setEmail(cliente.getEmail());
		}
	}

	public void deleteById(Integer id) {
		getClienteById(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException dx) {
			throw new DataIntegrityException(msgErroIntegrity + " ID: " + id, Cliente.class.getName());
		}
	}

	public List<ClienteDTO> findAll() {
		List<Cliente> cliente = clienteRepository.findAll();
		List<ClienteDTO> listDTO = cliente.stream().map(cat -> new ClienteDTO(cat)).collect(Collectors.toList());
		return listDTO;
	}

	public Page<ClienteDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Cliente> pageCat = clienteRepository.findAll(pageRequest);
		Page<ClienteDTO> listDTO = pageCat.map(cat -> new ClienteDTO(cat));
		return listDTO;
	}

	public Cliente fromDTO(ClienteDTO clienteDto) {
		return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), null, null);
	}
}