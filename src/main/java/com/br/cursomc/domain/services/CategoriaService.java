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

import com.br.cursomc.domain.Categoria;
import com.br.cursomc.domain.services.exeptions.DataIntegrityException;
import com.br.cursomc.domain.services.exeptions.ObjectNotFoundException;
import com.br.cursomc.dto.CategoriaDTO;
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
			throw new DataIntegrityException(msgErroIntegrity + " ID: " + id, Categoria.class.getName());
		}
	}

	public List<CategoriaDTO> findAll() {
		List<Categoria> categoria = categoriaRepository.findAll();
		List<CategoriaDTO> listDTO = categoria.stream().map(cat -> new CategoriaDTO(cat)).collect(Collectors.toList());
		return listDTO;
	}

	public Page<CategoriaDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Categoria> pageCat = categoriaRepository.findAll(pageRequest);
		Page<CategoriaDTO> listDTO = pageCat.map(cat -> new CategoriaDTO(cat));
		return listDTO;
	}
	
	public Categoria fromDTO(CategoriaDTO categoriaDto) {
		return new Categoria(categoriaDto.getId(), categoriaDto.getNome());
	}	
}