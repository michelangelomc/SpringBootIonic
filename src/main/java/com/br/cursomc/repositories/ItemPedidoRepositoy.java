package com.br.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.cursomc.domain.ItemPedido;
import com.br.cursomc.domain.ItemPedidoPk;

@Repository
public interface ItemPedidoRepositoy extends JpaRepository<ItemPedido, ItemPedidoPk> {

}
