package com.br.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.cursomc.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
