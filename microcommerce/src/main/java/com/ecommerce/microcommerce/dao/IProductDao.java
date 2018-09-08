package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IProductDao extends JpaRepository<Product, Long> {

	/**
	 * <b>EFFECTUER L'OPERATION DE RECHERCHE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)CRITERE DE RECHERCHE : L'ATTRIBUT 'PRIX'.<br/> 
	 * ->(02.)CONDITION A SATISFAIRE : L'ATTRIBUT DOIT ETRE PLUS GRAND QUE LA VALEUR LIMITE FOURNIE.<br/>
	 *    
	 * @param pPrixLimit La valeur limite fournie.
	 * @return List<Product> La liste de produits trouves.
	 */
    public abstract List<Product> findByPrixGreaterThan(int     pPrixLimit   );

	/**
	 * <b>EFFECTUER L'OPERATION DE RECHERCHE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)CRITERE DE RECHERCHE : L'ATTRIBUT 'PRIX'.<br/> 
	 * ->(02.)CONDITION A SATISFAIRE  : L'ATTRIBUT DOIT CONTENIR LA CHAINE FOURNIE.<br/>
	 *    
	 * @param pNomRecherche La chaine fournie.
	 * @return List<Product> La liste de produits trouves.
	 */
    public abstract List<Product> findByNomLike        (String  pNomRecherche);
    
	/**
	 * <b>EFFECTUER L'OPERATION DE RECHERCHE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)CRITERE DE RECHERCHE : L'ATTRIBUT 'NOM'.<br/> 
	 * ->(02.)CONDITION A SATISFAIRE  : L'ATTRIBUT DOIT ETRE TRIE PAR ORDRE ALPHABETIQUE CROISSANT.<br/>
	 *    
	 * @return List<Product> La liste de produits trouves.
	 */
    public abstract List<Product> findByOrderByNom    ();
}
