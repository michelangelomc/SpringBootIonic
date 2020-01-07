package com.br.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.br.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PagamentoBoleto extends Pagamento{

	private static final long serialVersionUID = 3348606610210336981L;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date data_vencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date data_pagamento;
	
	public PagamentoBoleto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PagamentoBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estadoPagamento, pedido);
		this.data_pagamento = dataPagamento;
		this.data_vencimento = dataVencimento;
	}

	public Date getData_vencimento() {
		return data_vencimento;
	}

	public void setData_vencimento(Date data_vencimento) {
		this.data_vencimento = data_vencimento;
	}

	public Date getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(Date data_pagamento) {
		this.data_pagamento = data_pagamento;
	}
}
