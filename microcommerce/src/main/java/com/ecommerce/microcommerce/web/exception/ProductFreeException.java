package com.ecommerce.microcommerce.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <b>EXCEPTION DU TYPE CI-DESSOUS :</b><br/>
 * TYPE : 'PRODUIT GRATUIT'
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductFreeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * <b>CONSTRUCTEUR AVEC UN ARGUMENT</b><br/>
     * @param pMessage Le message de l'exception
     */
    public ProductFreeException(String pMessage) {
        super(pMessage);
    }
}
