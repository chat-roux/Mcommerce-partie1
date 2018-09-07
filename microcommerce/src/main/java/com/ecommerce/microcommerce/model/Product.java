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
	private int id;

    @Length(min=3, max=20, message="Longueur non conforme aux exigences [Minimum : 3, Maximum : 20]")
    private String nom;

    @Min(value=1)
    private int prix;
    private int prixAchat;

    
    /**
     * CONSTRUCTEUR SANS ARGUMENTS
     */
    public Product() {
		LOGGER.info("CLASS : Product -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- BEGIN");
		LOGGER.info("CLASS : Product -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- BEGIN");
    }

    /**
     * CONSTRUCTEUR AVEC ARGUMENTS
     */
    public Product(int pId, String pNom, int pPrix, int pPrixAchat) {
    	
		LOGGER.info("CLASS : Product -- METHOD : CONSTRUCTEUR AVEC ARGUMENTS -- BEGIN");
		
        this.id        = pId;
        this.nom       = pNom;
        this.prix      = pPrix;
        this.prixAchat = pPrixAchat;
        
		LOGGER.info("CLASS : Product -- METHOD : CONSTRUCTEUR AVEC ARGUMENTS -- END");
    }

    
    public int    getId()        { return this.id;        }
    public String getNom()       { return this.nom;       }
    public int    getPrix()      { return this.prix;      }
    public int    getPrixAchat() { return this.prixAchat; }

    
    public void setId       (int    pId       ) { this.id        = pId;        }
    public void setNom      (String pNom      ) { this.nom       = pNom;       }
    public void setPrix     (int    pPrix     ) { this.prix      = pPrix;      }
    public void setPrixAchat(int    pPrixAchat) { this.prixAchat = pPrixAchat; }

    
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
