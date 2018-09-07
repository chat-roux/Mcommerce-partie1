package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.dao.IProductDao;
import com.ecommerce.microcommerce.exception.ProductNotFoundException;
import com.ecommerce.microcommerce.model.Product;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Api(description="Opérations de type 'CRUD' sur les produits.")
@RestController
public class ProductController {

	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
    private IProductDao productDao;

    @Autowired
    public void setProductDao (IProductDao pProductDao) {
        this.productDao = pProductDao;
    }

    @RequestMapping(value="/Produits", method= RequestMethod.GET)
    public MappingJacksonValue rechercherTous() {

		LOGGER.info("CLASS : ProductController -- METHOD : rechercherTous -- BEGIN");
        List<Product> products = this.productDao.findAll();
        
        SimpleBeanPropertyFilter excludePrixAchatPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");
        FilterProvider productFilterProvider = new SimpleFilterProvider().addFilter("productFilter", excludePrixAchatPropertyFilter);

        MappingJacksonValue productMappingJacksonValue = new MappingJacksonValue(products);
        productMappingJacksonValue.setFilters(productFilterProvider);
        
		LOGGER.info("CLASS : ProductController -- METHOD : rechercherTous -- END");
        return productMappingJacksonValue;
    }
    
	@ApiOperation(value = "Rechercher un produit par ID sous la condition que cet ID soit présent en BDD !")
    @RequestMapping(value = "/Produits/{id}", method = RequestMethod.GET)
    public Product rechercherParId(@PathVariable(name = "id") int pId) {

		LOGGER.info("CLASS : ProductController -- METHOD : rechercherParId -- BEGIN");
        Optional<Product> optionalProductFound = this.productDao.findById(pId);

        if (!optionalProductFound.isPresent()) {
    		LOGGER.info("CLASS : ProductController -- METHOD : rechercherParId -- END");
            throw new ProductNotFoundException("Produit introuvable -- Product-Id : [" + pId + "]");
        }
        Product productFound = optionalProductFound.get();

		LOGGER.info("CLASS : ProductController -- METHOD : rechercherParId -- END");
        return productFound;
    }
    
    @RequestMapping(value = "/Produits", method = RequestMethod.POST)
    public ResponseEntity<Void> ajouter(@Valid @RequestBody Product pProduct) {

		LOGGER.info("CLASS : ProductController -- METHOD : ajouter -- BEGIN");
        Product productSaved = this.productDao.save(pProduct);

        if (productSaved == null) {
            ResponseEntity<Void> responseEntity = ResponseEntity.noContent().build();
    		LOGGER.info("CLASS : ProductController -- METHOD : ajouter -- END");
            return responseEntity;
        }
        URI productSavedUri = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(productSaved.getId())
                            .toUri();
        ResponseEntity<Void> responseEntity = ResponseEntity.created(productSavedUri).build();

		LOGGER.info("CLASS : ProductController -- METHOD : ajouter -- END");
        return responseEntity;
    }
    
    @RequestMapping(value = "/Produits", method = RequestMethod.PUT)
    public void modifier(@RequestBody Product pProduct) {

		LOGGER.info("CLASS : ProductController -- METHOD : modifier -- BEGIN");
        this.productDao.save(pProduct);
		LOGGER.info("CLASS : ProductController -- METHOD : modifier -- END");
    }

    @RequestMapping(value = "/Produits/{id}", method = RequestMethod.DELETE)
    public void supprimer(@PathVariable(name = "id") int pId) {

		LOGGER.info("CLASS : ProductController -- METHOD : supprimer -- BEGIN");
        this.productDao.deleteById(pId);
		LOGGER.info("CLASS : ProductController -- METHOD : supprimer -- END");
    }
	
    @RequestMapping(value = "/test/Produits/prix/{prixLimit}", method = RequestMethod.GET)
    public List<Product> testRechercherParPrix(@PathVariable(name = "prixLimit") int pPrixLimit) {

		LOGGER.info("CLASS : ProductController -- METHOD : testRechercherParPrix -- BEGIN");
        List<Product> products = this.productDao.findByPrixGreaterThan(pPrixLimit);
		LOGGER.info("CLASS : ProductController -- METHOD : testRechercherParPrix -- END");
        return products;
    }

    @RequestMapping(value = "/test/Produits/nom/{nomRecherche}", method = RequestMethod.GET)
    public List<Product> testRechercherParNom(@PathVariable(name = "nomRecherche") String pNomRecherche) {

		LOGGER.info("CLASS : ProductController -- METHOD : testRechercherParNom -- BEGIN");
        List<Product> products = this.productDao.findByNomLike("%" + pNomRecherche + "%");
		LOGGER.info("CLASS : ProductController -- METHOD : testRechercherParNom -- END");
        return products;
    }
}
