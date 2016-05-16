package com.shop.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryJPA {

	private static FactoryJPA instance;
	private EntityManagerFactory emf;

	private FactoryJPA() {
		emf = Persistence.createEntityManagerFactory("PU");
	}

	public static FactoryJPA getInstanance() {
		if (instance == null) {
			instance = new FactoryJPA();
		}
		return instance;
	}

	public static EntityManager getEntityManager() {
		return getInstanance().emf.createEntityManager();
	}
}
