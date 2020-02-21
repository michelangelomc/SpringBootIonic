package com.br.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.br.cursomc.domain.services.DBService;

@Configuration
@Profile("teste")
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Value("$spring.jpa.hibernate.ddl-auto")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if (!"create".equals(strategy)) {
			dbService.instateateDatabase();
			return false;
		}
		return true;
	}
}