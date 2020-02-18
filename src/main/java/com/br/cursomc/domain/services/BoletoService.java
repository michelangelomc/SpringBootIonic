package com.br.cursomc.domain.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.br.cursomc.domain.PagamentoBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoBoleto pagamento, Date instantePedido) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(instantePedido);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pagamento.setData_vencimento(calendar.getTime());
	}
}
