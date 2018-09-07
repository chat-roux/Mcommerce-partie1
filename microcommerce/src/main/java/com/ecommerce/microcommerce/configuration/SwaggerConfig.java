package com.ecommerce.microcommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * <b>CLASSE EFFECTUANT LES TACHES SUIVANTES : </b><br/>
 *    ->CONFIGURER LES FONCTIONNALITES DE GENERATION DE LA DOCUMENTATION SUR LES API REST
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	
	/**
	 * <b>METHODE EFFECTUANT LES TACHES SUIVANTES : </b><br/>
	 *    ->EFFECTUER LA CONFIGURATION
	 */
    @Bean
    public Docket api() {
		
		System.out.println("CLASS : SwaggerConfig -- METHOD : api -- BEGIN");
		
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
											.select()
											.apis(RequestHandlerSelectors.basePackage("com.ecommerce.microcommerce.web"))
											.paths(PathSelectors.regex("/Produits.*"))
											.build();
									
		System.out.println("CLASS : SwaggerConfig -- METHOD : api -- END");
        return docket;
    }
}