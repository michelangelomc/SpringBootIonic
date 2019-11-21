package com.br.cursomc.domain.services.exeptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8718628711492498169L;

	private String mensage = "";
	
	public ObjectNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ObjectNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ObjectNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public ObjectNotFoundException(String message, String className) {
		super(message);
		this.mensage = message + " | Tipo: " + className;
	}

	public ObjectNotFoundException(Throwable cause) {
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
