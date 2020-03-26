package com.br.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.br.cursomc.domain.services.DBService;

import br.com.newtec.email_service.service.EmailService;
import br.com.newtec.email_service.service.impl.MockEmailService;

@Configuration
@Profile("teste")
public class TestConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instateateDatabase();
		return true;
	}

	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}