package com.br.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.cursomc.domain.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {
}