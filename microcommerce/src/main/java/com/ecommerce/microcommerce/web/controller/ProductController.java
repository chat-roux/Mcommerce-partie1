package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.dao.IProductDao;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.web.bean.ProductBean;
import com.ecommerce.microcommerce.web.exception.ProductFreeException;
import com.ecommerce.microcommerce.web.exception.ProductNotFoundException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(description="Opérations de type 'CRUD' sur les produits.")
@RestController
public class ProductController {

	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	private static final String ERROR_PRODUCT_NOT_FOUND_MESSAGE = "Erreur -- Produit introuvable";
	private static final String ERROR_PRODUCT_FREE_MESSAGE = "Erreur -- Produit gratuit";
	
    private IProductDao productDao;

    @Autowired
    public void setProductDao (IProductDao pProductDao) {
        this.productDao = pProductDao;
    }

	/**
	 * <b>EFFECTUER L'OPERATION DE RECHERCHE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)CRITERE DE RECHERCHE : AUCUN.<br/> 
	 * ->(02.)CONDITION A SATISFAIRE : AUCUNE.<br/>
	 *    
	 * @return MappingJacksonValue La liste de produits trouvee.
	 */
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
    
	/**
	 * <b>EFFECTUER L'OPERATION DE RECHERCHE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)CRITERE DE RECHERCHE : L'ATTRIBUT 'id'.<br/> 
	 * ->(02.)CONDITION A SATISFAIRE : L'ATTRIBUT 'id' EST EGAL A LA VALEUR FOURNIE.<br/>
	 *    
	 * @return Product Le produit trouve.
	 */
	@ApiOperation(value = "Rechercher un produit par ID sous la condition que cet ID soit présent en BDD !")
    @RequestMapping(value = "/Produits/{id}", method = RequestMethod.GET)
    public Product rechercherParId(@PathVariable(name = "id") Long pId) {

		LOGGER.info("CLASS : ProductController -- METHOD : rechercherParId -- BEGIN");
        Optional<Product> optionalProductFound = this.productDao.findById(pId);

        if (!optionalProductFound.isPresent()) {
    		LOGGER.info("CLASS : ProductController -- METHOD : rechercherParId -- END");
            throw new ProductNotFoundException(ERROR_PRODUCT_NOT_FOUND_MESSAGE + " -- " + "Product-Id : " + pId + "]");
        }
        Product productFound = optionalProductFound.get();

		LOGGER.info("CLASS : ProductController -- METHOD : rechercherParId -- END");
        return productFound;
    }
    
	/**
	 * <b>EFFECTUER L'OPERATION D'AJOUT CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)OBJET A AJOUTER : L'OBJET 'Product' FOURNI.<br/> 
	 *    
	 * @param pProduct L'objet 'Product' a ajouter.
	 * @return ResponseEntity<Product> Une réponse de type 'ResponseEntity<Product>'.
	 */
    @RequestMapping(value = "/Produits", method = RequestMethod.POST)
    public ResponseEntity<Product> ajouter(@Valid @RequestBody Product pProduct) {

		LOGGER.info("CLASS : ProductController -- METHOD : ajouter -- BEGIN");
		if (pProduct.getPrix() <= 0) {
			LOGGER.info("CLASS : ProductController -- METHOD : ajouter -- END");
			throw new ProductFreeException(ERROR_PRODUCT_FREE_MESSAGE + " -- " + "Product-Id : [" + pProduct.getId() + "]");
		}
        Product productSaved = this.productDao.save(pProduct);

        if (productSaved == null) {
            ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(productSaved, HttpStatus.NO_CONTENT);
    		LOGGER.info("CLASS : ProductController -- METHOD : ajouter -- END");
            return responseEntity;
        }
        /*
        URI productSavedUri = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(productSaved.getId())
                            .toUri();
        */
        //ResponseEntity<Product> responseEntity = ResponseEntity.created(productSavedUri).build();
        ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(productSaved, HttpStatus.CREATED);

		LOGGER.info("CLASS : ProductController -- METHOD : ajouter -- END");
        return responseEntity;
    }
    
	/**
	 * <b>EFFECTUER L'OPERATION DE MODIFICATION CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)OBJET A MODIFIER : L'OBJET 'Product' FOURNI.<br/> 
	 *    
	 * @param pProduct L'objet 'Product' a modifier.
	 * @return ResponseEntity<Product> Une réponse de type 'ResponseEntity<Product>'.
	 */
    @RequestMapping(value = "/Produits", method = RequestMethod.PUT)
    public ResponseEntity<Product> modifier(@RequestBody Product pProduct) {

		LOGGER.info("CLASS : ProductController -- METHOD : modifier -- BEGIN");
        Product productModified = this.productDao.save(pProduct);
        
        ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(productModified, HttpStatus.OK);
        
		LOGGER.info("CLASS : ProductController -- METHOD : modifier -- END");
		return responseEntity;
    }

	/**
	 * <b>EFFECTUER L'OPERATION DE SUPPRESSION CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)ID DE L'OBJET A SUPPRIMER : L'ID FOURNI.<br/> 
	 *    
	 * @param pId L'attribut 'id' de l'objet a supprimer.
	 * @return 
	 */
    @RequestMapping(value = "/Produits/{id}", method = RequestMethod.DELETE)
    public void supprimer(@PathVariable(name = "id") Long pId) {

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
    
	/**
	 * <b>EFFECTUER LE TRAITEMENT CI-DESSOUS :</b><br/>
	 * ->CALCULER LA MARGE DE BENEFICE SUR LE PRODUIT FOURNI.<br/>
	 * <br/>
	 *    
	 * @return List<ProductBean> La liste de beans d'affichage 'ProductBean'.
	 */
    @RequestMapping(value = "/AdminProduits", method = RequestMethod.GET)
    private Map<ProductBean, Float> calculerMarge() {
    	
		LOGGER.info("CLASS : ProductController -- METHOD : calculerMarge -- BEGIN");
		List<Product> productsFound = this.productDao.findAll();
		Map<ProductBean, Float> productBeans = new HashMap<ProductBean, Float>();
		
		for (Product product : productsFound) {
			ProductBean productBean = new ProductBean(product);
			productBeans.put(productBean, productBean.getMarge());
		}
		LOGGER.info("CLASS : ProductController -- METHOD : calculerMarge -- END");
    	return productBeans;
    }
    
	/**
	 * <b>EFFECTUER LE TRAITEMENT CI-DESSOUS :</b><br/>
	 * ->TRIER LES PRODUITS PAR ORDRE ALPHABETIQUE.<br/>
	 * <br/>
	 *    
	 * @return List<ProductBean> La liste de beans d'affichage 'ProductBean'.
	 */
    @RequestMapping(value = "/Produits/order/nom", method = RequestMethod.GET)
    private List<ProductBean> trierProduitsParNom() {
    	
		LOGGER.info("CLASS : ProductController -- METHOD : trierProduitsParNom -- BEGIN");
		List<Product> productsFound = this.productDao.findByOrderByNom();
		List<ProductBean> productBeans = new ArrayList<ProductBean>();
		
		for (Product product : productsFound) {
			ProductBean productBean = new ProductBean(product);
			productBeans.add(productBean);
		}
		LOGGER.info("CLASS : ProductController -- METHOD : trierProduitsParNom -- END");
    	return productBeans;
    }
}
