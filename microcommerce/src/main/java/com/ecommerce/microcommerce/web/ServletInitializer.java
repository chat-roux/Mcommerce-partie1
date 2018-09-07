package com.ecommerce.microcommerce.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.ecommerce.microcommerce.MicrocommerceApplication;

public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ServletInitializer.class);
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

		LOGGER.info("CLASS : ServletInitializer -- METHOD : configure -- BEGIN");
		
		SpringApplicationBuilder outputBuilder = builder.sources(MicrocommerceApplication.class);
		
		LOGGER.info("CLASS : ServletInitializer -- METHOD : configure -- BEGIN");
		return outputBuilder;
	}
}
