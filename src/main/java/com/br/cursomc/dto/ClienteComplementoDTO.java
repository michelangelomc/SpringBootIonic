package com.br.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.br.cursomc.domain.Cliente;
import com.br.cursomc.domain.services.validator.ClienteInsert;

@ClienteInsert
public class ClienteComplementoDTO implements Serializable {

	private static final long serialVersionUID = 3984973364487518431L;

	public ClienteComplementoDTO() {
		super();
	}	

	public ClienteComplementoDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
	}
	
	private int id;
	
	@NotEmpty(message = "Nome é obrigatório!")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres.")
	private String nome;
	
	@Email(message = "E-mail inválido!")
	private  String email;

	@NotEmpty(message = "Nome é obrigatório!")
	private String CpfCnpj;
	
	private Integer tipoCliente;
	
	@NotEmpty(message = "Nome é obrigatório!")
	private String logradouro;
	
	@NotEmpty(message = "Nome é obrigatório!")
	private String numero;
	
	private String complemento;
	private String bairro;
	
	@NotEmpty(message = "Nome é obrigatório!")
	private String cep;
	
	@NotEmpty(message = "Nome é obrigatório!")
	private String tel_celular;
	
	private String tel_fixo;
    private String tel_comercial;
	
	private Integer cidadeId;
	
	public String getCpfCnpj() {
		return CpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		CpfCnpj = cpfCnpj;
	}

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTel_fixo() {
		return tel_fixo;
	}

	public void setTel_fixo(String tel_fixo) {
		this.tel_fixo = tel_fixo;
	}

	public String getTel_celular() {
		return tel_celular;
	}

	public void setTel_celular(String tel_celular) {
		this.tel_celular = tel_celular;
	}

	public String getTel_comercial() {
		return tel_comercial;
	}

	public void setTel_comercial(String tel_comercial) {
		this.tel_comercial = tel_comercial;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
