package com.br.cursomc.dto;

import java.io.Serializable;

import com.br.cursomc.domain.Produto;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 3409810634229051320L;

	public ProdutoDTO() {
		super();
	}
	
	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
	    this.nome = produto.getNome();
	    this.preco = produto.getPreco();
	}

	private Integer id;
	private String nome;
	private Double preco;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
}
