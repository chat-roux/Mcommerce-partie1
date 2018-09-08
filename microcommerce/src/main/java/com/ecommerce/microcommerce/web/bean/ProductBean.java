package com.ecommerce.microcommerce.web.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecommerce.microcommerce.model.Product;

/**
 * <b>CET OBJET EST BEAN DE VUE.</b><br/>
 * <b>IL CONTIENT LES PROPRIETES AFFICHABLES D'UN PRODUIT</b><br/>
 * <br/>
 * @author 1603599
 */
public class ProductBean {
	
	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductBean.class);
	
	private Long id;
    private String nom;
    private float prix;
    private float prixAchat;
    private float marge;

    
    /**
     * CONSTRUCTEUR SANS ARGUMENTS
     */
    public ProductBean() {
    	
		LOGGER.info("CLASS : ProductBean -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- BEGIN");
		LOGGER.info("CLASS : ProductBean -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- END");
    }

    /**
     * CONSTRUCTEUR AVEC ARGUMENTS
     * 
     * @param pId
     * @param pNom
     * @param pPrix
     * @param pPrixAchat
     */
    public ProductBean(Long pId, String pNom, float pPrix, float pPrixAchat) {
    	
		LOGGER.info("CLASS : ProductBean -- METHOD : CONSTRUCTEUR AVEC ARGUMENTS -- BEGIN");
		
        this.id        = pId;
        this.nom       = pNom;
        this.prix      = pPrix;
        this.prixAchat = pPrixAchat;
        this.marge = this.calulerMarge(pPrix, pPrixAchat);
        
		LOGGER.info("CLASS : ProductBean -- METHOD : CONSTRUCTEUR AVEC ARGUMENTS -- END");
    }

    /**
     * CONSTRUCTEUR DE RECOPIE
     * 
     * @param pProduct
     */
    public ProductBean(Product pProduct) {
    	
		LOGGER.info("CLASS : ProductBean -- METHOD : CONSTRUCTEUR DE RECOPIE -- BEGIN");
		
        this.id        = pProduct.getId();
        this.nom       = pProduct.getNom();
        this.prix      = pProduct.getPrix();
        this.prixAchat = pProduct.getPrixAchat();
        
        this.marge = this.calulerMarge(pProduct.getPrix(), pProduct.getPrixAchat());
        
		LOGGER.info("CLASS : ProductBean -- METHOD : CONSTRUCTEUR DE RECOPIE -- END");
    }

    
    public Long   getId()        { return this.id;        }
    public String getNom()       { return this.nom;       }
    public float  getPrix()      { return this.prix;      }
    public float  getPrixAchat() { return this.prixAchat; }
    public float  getMarge()     { return this.marge;     }

    
    public void setId       (Long   pId       ) { this.id        = pId;        }
    public void setNom      (String pNom      ) { this.nom       = pNom;       }
    public void setPrix     (float  pPrix     ) { this.prix      = pPrix;      }
    public void setPrixAchat(float  pPrixAchat) { this.prixAchat = pPrixAchat; }
    public void setMarge    (float  pMarge    ) { this.marge     = pMarge;     }

    
    @Override
    public String toString(){
    	String description = "Product" + "{" 
			    									+ "id = "       + this.id  
			    							+ ", " + "nom = "       + this.nom
			    							+ ", " + "prix = "      + this.prix 
			    							//+ ", " + "prixAchat = " + this.prixAchat  
			    							//+ ", " + "marge = "     + this.marge  // ON EXCLUT LA MARGE
			    						+ "}";
        return description;
    }
    
    /**
     * CALCULER LA MARGE DE BENEFICE SUR LE PRODUIT
     * 
     * @param pPrix Le prix de vente du produit
     * @param pPrixAchat Le prix d'achat du produit
     * @return La marge de benefice sur le produit.
     */
    private float calulerMarge(float pPrix, float pPrixAchat) {

		LOGGER.info("CLASS : ProductBean -- METHOD : calulerMarge -- BEGIN");
    	float result = pPrix - pPrixAchat;
		LOGGER.info("CLASS : ProductBean -- METHOD : calulerMarge -- END");
    	return result;
    }
}
