package com.br.cursomc.domain;

import javax.persistence.Entity;

import com.br.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoCartao extends Pagamento{

	private static final long serialVersionUID = -4288796293200915221L;
	
	private Integer numero_parcelas;

	public PagamentoCartao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PagamentoCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroParcela) {
		super(id, estadoPagamento, pedido);
	
		this.numero_parcelas = numeroParcela;
	}

	public Integer getNumero_parcelas() {
		return numero_parcelas;
	}

	public void setNumero_parcelas(Integer numero_parcelas) {
		this.numero_parcelas = numero_parcelas;
	}
	
}
