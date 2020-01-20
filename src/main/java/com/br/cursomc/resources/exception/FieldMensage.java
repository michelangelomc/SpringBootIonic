package com.br.cursomc.resources.exception;

import java.io.Serializable;

public class FieldMensage implements Serializable {

	private static final long serialVersionUID = -1626307138343463472L;

	public FieldMensage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		Message = message;
	}

	private String fieldName;
	private String Message;
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public FieldMensage() {
		super();
	}
}
