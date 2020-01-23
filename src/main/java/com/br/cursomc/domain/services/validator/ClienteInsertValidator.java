package com.br.cursomc.domain.services.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.br.cursomc.domain.enums.TipoCliente;
import com.br.cursomc.domain.services.validator.util.ResorcesUtils;
import com.br.cursomc.dto.ClienteComplementoDTO;
import com.br.cursomc.resources.exception.FieldMensage;

/*
  BY adrianoluis -->  https://gist.github.com/adrianoluis/5043397d378ae506d87366abb0ab4e30
 */
public abstract class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteComplementoDTO> {

	private final String msgTipoCliente = "Tipo de cliente não pode ser nulo!";
	private final String msgCpfInvalid = "Cpf não é valido!";
	private final String msgCnpjInvalid = "Cnpj não é valido!";

	@Override
	public boolean isValid(ClienteComplementoDTO value, ConstraintValidatorContext context) {
		List<FieldMensage> list = new ArrayList<>();

		if (value.getTipoCliente() == null)
			list.add(new FieldMensage("Tipo Cliente", msgTipoCliente));

		if (value.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCodigo())
				&& !ResorcesUtils.isValidCpf(value.getCpfCnpj()))
			list.add(new FieldMensage("Tipo Cliente", msgCpfInvalid));

		if (value.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCodigo())
				&& !ResorcesUtils.isValidCnpj(value.getCpfCnpj()))
			list.add(new FieldMensage("Tipo Cliente", msgCnpjInvalid));

		for (FieldMensage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
					.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}
