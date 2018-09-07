package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductDao extends JpaRepository<Product, Integer> {

    public abstract List<Product> findByPrixGreaterThan(int     pPrixLimit   );
    public abstract List<Product> findByNomLike        (String  pNomRecherche);
}
