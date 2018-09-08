package com.ecommerce.microcommerce.model;

import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

/**
 * <b>CET OBJET EST UNE ENTITE.</b><br/>
 * <b>IL CONTIENT LES PROPRIETES PERSISTABLES D'UN PRODUIT</b><br/>
 * <br/>
 * @author 1603599
 */
//@JsonFilter("ProductFilter")
@Entity
public class Product {

	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	@Transient
	private static final Logger LOGGER = LoggerFactory.getLogger(Product.class);
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Length(min=3, max=20, message="Longueur non conforme aux exigences [Minimum : 3, Maximum : 20]")
    private String nom;

    @Min(value=0, message="Valeur non conforme aux exigences [Minimum : 0.000001]")
    private float prix;
    private float prixAchat;

    
    /**
     * CONSTRUCTEUR SANS ARGUMENTS
     */
    public Product() {
    	
		LOGGER.info("CLASS : Product -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- BEGIN");
		LOGGER.info("CLASS : Product -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- END");
    }

    /**
     * CONSTRUCTEUR AVEC ARGUMENTS
     */
    public Product(Long pId, String pNom, float pPrix, float pPrixAchat) {
    	
		LOGGER.info("CLASS : Product -- METHOD : CONSTRUCTEUR AVEC ARGUMENTS -- BEGIN");
		
        this.id        = pId;
        this.nom       = pNom;
        this.prix      = pPrix;
        this.prixAchat = pPrixAchat;
        
		LOGGER.info("CLASS : Product -- METHOD : CONSTRUCTEUR AVEC ARGUMENTS -- END");
    }

    
    public Long   getId()        { return this.id;        }
    public String getNom()       { return this.nom;       }
    public float  getPrix()      { return this.prix;      }
    public float  getPrixAchat() { return this.prixAchat; }

    
    public void setId       (Long   pId       ) { this.id        = pId;        }
    public void setNom      (String pNom      ) { this.nom       = pNom;       }
    public void setPrix     (float  pPrix     ) { this.prix      = pPrix;      }
    public void setPrixAchat(float  pPrixAchat) { this.prixAchat = pPrixAchat; }

    
    @Override
    public String toString(){
    	String description = "Product" + "{" 
			    									+ "id = "       + this.id  
			    							+ ", " + "nom = "       + this.nom
			    							+ ", " + "prix = "      + this.prix 
			    							+ ", " + "prixAchat = " + this.prixAchat  
			    						+ "}";
        return description;
    }
}
