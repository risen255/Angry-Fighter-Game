package com.shop.controller.managedbeans;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shop.controller.managedbeans.credentials.RegisterCredential;
import com.shop.model.dao.UserDAO;
import com.shop.model.dto.User;

@ManagedBean(name = "register")
@RequestScoped
public class Register {
	
	static final Logger LOG = LogManager.getLogger(Register.class);
	
	@ManagedProperty(value="#{registerCredential}")
	private RegisterCredential registerCredential;

	public Register() {
		
	}

	public void setRegisterCredential(RegisterCredential registerCredential) {
		this.registerCredential = registerCredential;
	}
	
	public String doRegister() {
		
		LOG.entry();
		
		User newUser = new User();
		newUser.setUsername(this.registerCredential.getUsername());
		newUser.setPassword(this.registerCredential.getPassword());
		newUser.setEmail(this.registerCredential.getEmail());
		newUser.setPhoneNumber(this.registerCredential.getPhoneNumber());
		
		UserDAO db = new UserDAO();
		db.save(newUser);
					
		LOG.exit();
		return "redirected//registered-page.xhtml";
	}
}
