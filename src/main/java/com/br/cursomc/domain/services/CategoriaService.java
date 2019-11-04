package com.br.cursomc.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cursomc.domain.Categoria;
import com.br.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Optional<Categoria> getCategoriaById(Integer id) {
		try {
			Optional<Categoria> categoria = categoriaRepository.findById(id);
			return categoria;
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}
}
