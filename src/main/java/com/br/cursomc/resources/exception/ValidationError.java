package com.br.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandarError {

	private static final long serialVersionUID = -9087480645916007966L;

	public ValidationError(Integer status, String mensage, Long timeStamp) {
		super(status, mensage, timeStamp);
	}

	private List<FieldMensage> fieldMessages = new ArrayList<>();

	public List<FieldMensage> getFieldMessages() {
		return fieldMessages;
	}

	public void addError(String fieldName, String messagem) {
		fieldMessages.add(new FieldMensage(fieldName, messagem));
	}
}
