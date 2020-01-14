package com.br.cursomc.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.br.cursomc.domain.Categoria;
import com.br.cursomc.domain.services.exeptions.DataIntegrityException;
import com.br.cursomc.domain.services.exeptions.ObjectNotFoundException;
import com.br.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	private final String msgErro = "Objeto não foi encontrado id: ";
	private final String msgErroIntegrity = "Nao é possivel excluir uma que categoria, pois possui produto relacionado.";

	public Optional<Categoria> getCategoriaById(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);

		categoria.orElseThrow(() -> new ObjectNotFoundException(msgErro + id, Categoria.class.getName()));

		return categoria;
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		getCategoriaById(categoria.getId());
		return categoriaRepository.save(categoria);
	}

	public void deleteById(Integer id) {
		getCategoriaById(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException dx) {
			throw new DataIntegrityException(msgErroIntegrity + " ID: "+ id, Categoria.class.getName());
		}
	}
}