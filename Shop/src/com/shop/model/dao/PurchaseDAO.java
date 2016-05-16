package com.shop.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shop.model.dto.Purchase;

public class PurchaseDAO extends GenericDAO<Purchase, Integer> {
	
	static final Logger LOG = LogManager.getLogger(PurchaseDAO.class);
	
	public List<Purchase> getAllPurchases() {
		
		LOG.entry();
		
		try {
			EntityManager em = this.getEntityManager();
			TypedQuery<Purchase> query = em.createNamedQuery(Purchase.getAllPurchases, Purchase.class);
			List<Purchase> purchaseList = query.getResultList();
			em.close();
			
			LOG.exit();
			return purchaseList;
		} catch(NoResultException e) {
			LOG.info(e.toString());
			LOG.exit();
			return null;
		}
	}
}
