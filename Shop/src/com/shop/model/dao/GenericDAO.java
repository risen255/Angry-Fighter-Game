package com.shop.model.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;

import com.shop.factory.FactoryJPA;

public abstract class GenericDAO<T, K> {

	private final Class<T> type;

	public GenericDAO() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	public void save(T t) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}

	public void delete(T t) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		t = em.merge(t);
		em.remove(t);
		em.getTransaction().commit();
		em.close();
	}

	public void update(T t) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
	}

	public T findById(K id) {
		EntityManager em = getEntityManager();
		T dto = em.find(type, id);
		em.close();
		return dto;
	}

	protected EntityManager getEntityManager() {
		return FactoryJPA.getEntityManager();
	}
}
