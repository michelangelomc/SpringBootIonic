package com.br.cursomc.domain.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cursomc.domain.ItemPedido;
import com.br.cursomc.domain.PagamentoBoleto;
import com.br.cursomc.domain.Pedido;
import com.br.cursomc.domain.enums.EstadoPagamento;
import com.br.cursomc.domain.services.exeptions.ObjectNotFoundException;
import com.br.cursomc.repositories.ItemPedidoRepositoy;
import com.br.cursomc.repositories.PagamentoRepository;
import com.br.cursomc.repositories.PedidoRepository;
import com.br.cursomc.repositories.ProdutoRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.newtec.email_service.request.entity.EmailUser;
import br.com.newtec.email_service.service.EmailService;
import br.com.newtec.email_service.service.impl.SmtpEmailService;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
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

	@Autowired
	private ClienteService clienteService;

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
		pedido.setCliente(clienteService.findId(pedido.getCliente().getId()));
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
			itemP.setProduto(produtoRepository.findById(itemP.getProduto().getId()).get());
			itemP.setPrecos(itemP.getProduto().getPreco());
			itemP.setPedido(pedido);
		}

		itemPedidoRepositoy.saveAll(pedido.getItens());

		EmailUser emailUser = getEmailUser(pedido);

		EmailService emailService = new SmtpEmailService();
		emailService.sendEmailConfirmation(emailUser);

		return pedido;
	}

	private EmailUser getEmailUser(Pedido pedido) {
		EmailUser emailUser = new EmailUser();
		emailUser.setDesc_email("Pedido de Compra");
		emailUser.setDesc_to(pedido.getCliente().getEmail());
		emailUser.setMensage(pedido.toString());
		return emailUser;
	}

	protected String htmlFromTemplate(Pedido pedido) {

		return "";
	}
}