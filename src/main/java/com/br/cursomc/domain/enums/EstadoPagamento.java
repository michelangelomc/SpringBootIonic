package com.br.cursomc.domain.enums;

public enum EstadoPagamento {
	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private int tipo;
	private String descricao;

	private EstadoPagamento(int tipo, String descricao) {
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public int getTipo() {
		return tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer id) {
		if (id == null)
			return null;

		for (EstadoPagamento ep : EstadoPagamento.values()) {
			if (id.equals(ep.getTipo()))
				return ep;
		}
		
		throw new IllegalArgumentException("ID inválido: " + id);
	}
}
