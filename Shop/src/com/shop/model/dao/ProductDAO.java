package com.shop.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shop.model.dto.Product;

public class ProductDAO extends GenericDAO<Product, Integer> {
	
	static final Logger LOG = LogManager.getLogger(ProductDAO.class);
	
	public List<Product> getAllProducts() {
		
		LOG.entry();
		
		try {
			EntityManager em = this.getEntityManager();
			TypedQuery<Product> query = em.createNamedQuery(Product.getAllProducts, Product.class);
			List<Product> productList = query.getResultList();
			em.close();
			
			LOG.exit();
			return productList;
		} catch(NoResultException e) {
			LOG.info(e.toString());
			LOG.exit();
			return null;
		}
	}
}
