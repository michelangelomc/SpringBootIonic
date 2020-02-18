package com.br.cursomc.domain.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cursomc.domain.ItemPedido;
import com.br.cursomc.domain.PagamentoBoleto;
import com.br.cursomc.domain.Pedido;
import com.br.cursomc.domain.Produto;
import com.br.cursomc.domain.enums.EstadoPagamento;
import com.br.cursomc.domain.services.exeptions.ObjectNotFoundException;
import com.br.cursomc.repositories.ItemPedidoRepositoy;
import com.br.cursomc.repositories.PagamentoRepository;
import com.br.cursomc.repositories.PedidoRepository;
import com.br.cursomc.repositories.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepositoy;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ItemPedidoRepositoy itemPedidoRepositoy;

	private final String msgErro = "Objeto não foi encontrado id: ";

	public Optional<Pedido> getPedidoById(Integer id) {
		Optional<Pedido> cliente = pedidoRepository.findById(id);

		cliente.orElseThrow(() -> new ObjectNotFoundException(msgErro + id, Pedido.class.getName()));

		return cliente;
	}

	@Transactional
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.getPagamento().setPedido(pedido);
		pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);

		if (pedido.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagamentoBoleto = (PagamentoBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagamentoBoleto, pedido.getInstante());
		}
		
		pedido = pedidoRepository.save(pedido);
		pagamentoRepositoy.save(pedido.getPagamento());
		
		for (ItemPedido itemP : pedido.getItens()) {
			itemP.setDesconto(0D);
			Produto id = produtoRepository.findById(itemP.getProduto().getId()).get();
			itemP.setPrecos(id.getPreco());
			itemP.setPedido(pedido);
		}
		
		itemPedidoRepositoy.saveAll(pedido.getItens());
		
		return pedido;
	}
}