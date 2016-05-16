package com.shop.controller.managedbeans;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.shop.model.dao.ProductDAO;
import com.shop.model.dto.Product;
import com.shop.xml.XmlReader;

@ManagedBean(name = "wholesaleManager")
@RequestScoped
public class WholesaleManager {

	public WholesaleManager() {
		
	}
	
	public String loadProducts() {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		
		XmlReader reader = new XmlReader();
		List<Product> productList = reader.getProductsList(ec);
		
		if(productList.size() > 0) {

			ProductDAO productDao = new ProductDAO();
			
			for(Product product : productList) {
				productDao.save(product);
			}
			
			fc.addMessage("userMessageSuccess", new FacesMessage("Produkty zosta³y odczytane z pliku xml i za³adowane do bazy danych."));
			fc.getExternalContext().getFlash().setKeepMessages(true);

		} else {
			fc.addMessage("userMessageFail", new FacesMessage("Lista produktów z pliku xml jest pusta."));
			fc.getExternalContext().getFlash().setKeepMessages(true);
		}
		
		// Redirect to main panel
		try {
            ec.redirect("panel.xhtml");
        } catch (IOException e) {
        	 
        }
		
		return "";
	}
}
