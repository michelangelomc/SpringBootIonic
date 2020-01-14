package com.br.cursomc.domain.services.exeptions;

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = -8718628711492498169L;

	private String mensage = "";
	
	public DataIntegrityException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DataIntegrityException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DataIntegrityException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DataIntegrityException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public DataIntegrityException(String message, String className) {
		super(message);
		this.mensage = message + " | Tipo: " + className;
	}

	public DataIntegrityException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public String getMensage() {
		return mensage;
	}

	public void setMensage(String menssage) {
		this.mensage = menssage;
	}	
}
