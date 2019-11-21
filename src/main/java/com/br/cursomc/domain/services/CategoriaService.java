package com.br.cursomc.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cursomc.domain.Categoria;
import com.br.cursomc.domain.services.exeptions.ObjectNotFoundException;
import com.br.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	private final String msgErro = "Objeto não foi encontrado id: ";

	public Optional<Categoria> getCategoriaById(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		
		categoria.orElseThrow(() -> new ObjectNotFoundException(msgErro + id, Categoria.class.getName()));
		
		return categoria;
	}
}