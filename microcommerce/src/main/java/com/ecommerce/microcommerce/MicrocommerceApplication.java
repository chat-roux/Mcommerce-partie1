package com.ecommerce.microcommerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <b>POINT D'ENTREE DE L'APPLICATION</b><br/>
 */
@EnableSwagger2
@SpringBootApplication
public class MicrocommerceApplication {

	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MicrocommerceApplication.class);
		
	/**
	 * <b>METHODE DE LANCEMENT</b><br/>
	 */
	public static void main(String[] args) {
		
		LOGGER.info("CLASS : MicrocommerceApplication -- METHOD : main -- BEGIN");
		
		SpringApplication.run(MicrocommerceApplication.class, args);
		
		LOGGER.info("CLASS : MicrocommerceApplication -- METHOD : main -- BEGIN");
	}
}
