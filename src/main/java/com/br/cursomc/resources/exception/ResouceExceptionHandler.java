package com.br.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.br.cursomc.domain.services.exeptions.DataIntegrityException;
import com.br.cursomc.domain.services.exeptions.ObjectNotFoundException;

@ControllerAdvice
public class ResouceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandarError> objectNotFound(ObjectNotFoundException objectNotFoundException,
			HttpServletRequest httpServletRequest) {
		StandarError standarError = new StandarError(HttpStatus.NOT_FOUND.value(), objectNotFoundException.getMensage(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standarError);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandarError> integretionViolation(DataIntegrityException dataIntegrityException,
			HttpServletRequest httpServletRequest) {
		StandarError standarError = new StandarError(HttpStatus.BAD_REQUEST.value(),
				dataIntegrityException.getMensage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standarError);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException methodArgumentNotValidException,
			HttpServletRequest httpServletRequest) {
		ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(),
				"Erro de validação", System.currentTimeMillis());

		for (FieldError field : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
			validationError.addError(field.getField(), field.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
	}
}
