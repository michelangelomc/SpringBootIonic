package com.br.cursomc.resources.exception;

import java.io.Serializable;

public class StandarError implements Serializable{

	private static final long serialVersionUID = -1789912386785760769L;

	private Integer status;
	private String mensage;
	private Long timeStamp;
	
	public StandarError(Integer status, String mensage, Long timeStamp) {
		super();
		this.status = status;
		this.mensage = mensage;
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMensage() {
		return mensage;
	}

	public void setMensage(String mensage) {
		this.mensage = mensage;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
