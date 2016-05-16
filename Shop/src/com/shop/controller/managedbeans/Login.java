package com.shop.controller.managedbeans;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shop.controller.managedbeans.credentials.LoginCredential;
import com.shop.model.dao.UserDAO;
import com.shop.model.dto.User;

@ManagedBean(name = "login")
@SessionScoped
public class Login {
	
	static final Logger LOG = LogManager.getLogger(Login.class);
	
	@ManagedProperty(value="#{loginCredential}")
	private LoginCredential loginCredential;
	
	private User user;
	
	public Login() {
		

	}

	public void setLoginCredential(LoginCredential loginCredential) {
		this.loginCredential = loginCredential;
	}

	public boolean isLoggedIn() {
		if(this.user != null)
			return true;
		
		return false;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String doLogin() {
		
		LOG.entry();

		System.out.println("login: " + this.loginCredential.getUsername());
		System.out.println("pass: " + this.loginCredential.getPassword());
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		if(this.isLoggedIn() == false) {
		
			UserDAO dao = new UserDAO();
			User checkUser = dao.getByUsername(this.loginCredential.getUsername());
	
			if (checkUser != null) {
	
				if (checkUser.getPassword().equals(this.loginCredential.getPassword()) == true) {
					LOG.info("Uzytkownik zostal pomyslnie zalogowany");
					
					fc.addMessage("userMessageSuccess", new FacesMessage("WLASNIE SIE ZALOGOWALES, GRATULACJE"));
					
					this.user = checkUser; // Log In
				} else {
					LOG.info("ZLE HASLO: " + this.loginCredential.getPassword() + " " + checkUser.getPassword());
					fc.addMessage("userMessageFail", new FacesMessage("Podane haslo lub login sa nieprawdilowe"));
				}
			} else {
				LOG.info("Uzytkownik nie istnieje");
				fc.addMessage("userMessageFail", new FacesMessage("Podane haslo lub login sa nieprawdilowe"));
			}
			LOG.exit();
		} else {
			LOG.info("Ten uzytkownik juz jest zalogowany. Nie mozna zalogowac sie od nowa.");
			fc.addMessage("userMessageFail", new FacesMessage("Jesteœ juz zalogowany, musisz siê najpierw wylogowaæ."));
		}

		return "";
	}
	
	public String doLogout() {
		LOG.entry();
		this.user = null;
		
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		
		try {		
		    ec.redirect("index.xhtml");
		} catch (IOException ex) {
			LOG.error(ex.toString());
		}
		
		LOG.exit();
		
		return "";
	}
}
