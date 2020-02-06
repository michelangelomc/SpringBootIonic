package com.br.cursomc.domain.services.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.br.cursomc.domain.Cliente;
import com.br.cursomc.dto.ClienteDTO;
import com.br.cursomc.repositories.ClienteRepository;
import com.br.cursomc.resources.exception.FieldMensage;


public abstract class ClientUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	private final String msgEmailExist = "Email já existente!";
	
	@Autowired
    private ClienteRepository clienteRepositoy;
	

	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Override
	public boolean isValid(ClienteDTO value, ConstraintValidatorContext context) {
		
		getIdByServlet();
		
		List<FieldMensage> list = new ArrayList<>();
		
		Cliente clientEmail = clienteRepositoy.findByEmail(value.getEmail());
		
		if (clientEmail != null && clientEmail.getId().equals(getIdByServlet())) 
			list.add(new FieldMensage("E-mail", msgEmailExist));
		
		
		for (FieldMensage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
					.addPropertyNode(fieldMessage.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}

	private Integer getIdByServlet() {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		return Integer.parseInt(map.get("id"));
	}
}
