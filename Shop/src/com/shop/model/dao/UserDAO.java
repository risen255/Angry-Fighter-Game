package com.shop.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shop.model.dto.User;

public class UserDAO extends GenericDAO<User, Integer> {
	
	static final Logger LOG = LogManager.getLogger(UserDAO.class);

	public User getByUsername(String username) {
		
		LOG.entry();
		
		try {
			EntityManager em = this.getEntityManager();
			TypedQuery<User> query = em.createNamedQuery(User.getByUsername, User.class);
			query.setParameter(1, username); // Username
			User user = query.getSingleResult();
			em.close();
			
			LOG.log(Level.TRACE, "User: " + username + " found");
			
			LOG.exit();
			return user;
			
		} catch(NoResultException e) {
			LOG.info(e.toString());
			LOG.exit();
			return null;
		}
	}
	
	public User getByEmail(String email) {
		
		LOG.entry();
		
		try {
			EntityManager em = this.getEntityManager();
			TypedQuery<User> query = em.createNamedQuery(User.getByEmail, User.class);
			query.setParameter(1, email); // Email
			User user = query.getSingleResult();
			em.close();
			
			LOG.log(Level.TRACE, "Email: " + email + " found");
			
			LOG.exit();
			return user;
			
		} catch(NoResultException e) {
			LOG.info(e.toString());
			LOG.exit();
			return null;
		}
	}
	
	public List<User> getAllUsers() {
		
		LOG.entry();
		
		try {
			EntityManager em = this.getEntityManager();
			TypedQuery<User> query = em.createNamedQuery(User.getAllUsers, User.class);
			List<User> userList = query.getResultList();
			em.close();
			
			LOG.exit();
			return userList;
		} catch(NoResultException e) {
			LOG.info(e.toString());
			LOG.exit();
			return null;
		}
	}
}
