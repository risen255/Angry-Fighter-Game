package com.shop.controller.managedbeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;

import com.shop.factory.CookieHelper;
import com.shop.model.dao.PurchaseDAO;
import com.shop.model.dto.Purchase;

@ManagedBean(name = "purchaseManager")
@RequestScoped
public class PurchaseManager {
	
	private List<Purchase> purchaseList;
	
	private int quantity;
	
	private String notRealized;

	public PurchaseManager() {
		
	}
	
	@PostConstruct
    public void init() {
		notRealized = "no";
		
		Cookie cookieNotRealized = CookieHelper.getCookie("notRealized");
		if(cookieNotRealized != null) {
			notRealized = cookieNotRealized.getValue();
		} else {
			notRealized = "no";
		}
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getNotRealized() {
		return notRealized;
	}

	public void setNotRealized(String notRealized) {
		this.notRealized = notRealized;
	}

	public List<Purchase> getPurchaseList() {
		
		PurchaseDAO purchaseDao = new PurchaseDAO();
		this.purchaseList = purchaseDao.getAllPurchases();
		
		CookieHelper.setCookie("notRealized", this.notRealized);
		
		this.sortPurchases();
		
		return this.purchaseList;
	}
	
	public String deletePurchase(String purID) {
		
		int purchaseID = Integer.parseInt(purID);
		
		PurchaseDAO purchaseDao = new PurchaseDAO();
		Purchase purchase = purchaseDao.findById(purchaseID);
		
		if(purchase != null) {
			purchaseDao.delete(purchase);
			
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage("userMessageSuccess", new FacesMessage("Zamówienie zosta³o usuniête z bazy danych."));
		}
		
		return "";
	}
	
	public String editPurchase(String purID) {
		
		System.out.println("PurchaseID: " + purID);
		System.out.println("Quantity: " + this.getQuantity());
		
		int purchaseID = Integer.parseInt(purID);
		
		PurchaseDAO purchaseDao = new PurchaseDAO();
		Purchase purchase = purchaseDao.findById(purchaseID);
		
		if(purchase != null) {
			purchase.setQuantity(this.getQuantity());
			
			purchaseDao.update(purchase);
			
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage("userMessageSuccess", new FacesMessage("Zamówienie zosta³o zaktualizowane."));
			fc.getExternalContext().getFlash().setKeepMessages(true);
		}
		
		try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("panel-purchases.xhtml");
         } catch (IOException e) {
        	 
         }
		
		return "";
	}
	
	private void sortPurchases() {
		
		if(this.notRealized.equals("yes") == true) {
			
			List<Purchase> notRealizedList = new ArrayList<Purchase>();
			
			for(Purchase purchase : this.purchaseList) {
				
				if(purchase.getRealized().equals("Nie") == true) {
					notRealizedList.add(purchase);
				}
			}
			
			this.purchaseList = notRealizedList;
		}
	}
}
