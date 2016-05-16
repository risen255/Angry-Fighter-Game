package com.shop.controller.managedbeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;

import com.shop.comparators.IDComparatorAscending;
import com.shop.comparators.IDComparatorDescending;
import com.shop.comparators.PriceComparatorAscending;
import com.shop.comparators.PriceComparatorDescending;
import com.shop.comparators.QuantityComparatorAscending;
import com.shop.comparators.QuantityComparatorDescending;
import com.shop.factory.CookieHelper;
import com.shop.model.dao.ProductDAO;
import com.shop.model.dto.Product;

@ManagedBean(name = "productManager")
@RequestScoped
public class ProductManager {
	
	private List<Product> productList;
	
	private int id;
	private String name;
	private String category;
	private int price;
	private int quantity;
	
	private String sorting;
	private String fieldSorting;
	private String exhausting; // Wyczerpywanie sie produktów (poni¿ej 10 w³¹cznie)

	public ProductManager() {
		
	}
	
	@PostConstruct
    public void init() {
        sorting = "ascending";
        fieldSorting = "id";
        exhausting = "no";
		
		Cookie cookieSorting = CookieHelper.getCookie("sorting");
		if(cookieSorting != null) {
			sorting = cookieSorting.getValue();
		} else {
			sorting = "ascending";
		}
        
		Cookie cookieFieldSorting = CookieHelper.getCookie("fieldSorting");
		if(cookieFieldSorting != null) {
			fieldSorting = cookieFieldSorting.getValue();
		} else {
			fieldSorting = "id";
		}
		
		Cookie cookieExhausting = CookieHelper.getCookie("exhausting");
		if(cookieExhausting != null) {
			exhausting = cookieExhausting.getValue();
		} else {
			exhausting = "id";
		}
    }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSorting() {
		return sorting;
	}

	public void setSorting(String sorting) {
		this.sorting = sorting;
	}

	public String getFieldSorting() {
		return fieldSorting;
	}

	public void setFieldSorting(String fieldSorting) {
		this.fieldSorting = fieldSorting;
	}

	public String getExhausting() {
		return exhausting;
	}

	public void setExhausting(String exhausting) {
		this.exhausting = exhausting;
	}

	public List<Product> getProductList() {
		
		ProductDAO productDao = new ProductDAO();
		this.productList = productDao.getAllProducts();
		
		CookieHelper.setCookie("sorting", this.sorting);
		CookieHelper.setCookie("fieldSorting", this.fieldSorting);
		CookieHelper.setCookie("exhausting", this.exhausting);
		
		if(this.exhausting.equals("yes") == true) {
			List<Product> exhaustedList = new ArrayList<Product>();
			
			for(Product product : this.productList) {
				if(product.getQuantity() <= 10) { // Je¿eli produkt siê wyczerpuje
					exhaustedList.add(product);
				}
			}
			
			// Aktualizacja listy produktów wyczerpuj¹cych siê
			this.productList = exhaustedList;
		}
		
		this.sortProducts();
		
		return this.productList;
	}
	
	public String addProduct() {
		
		Product product = new Product();
		product.setName(this.getName());
		product.setCategory(this.getCategory());
		product.setPrice(this.getPrice());
		product.setQuantity(this.getQuantity());
		
		ProductDAO productDao = new ProductDAO();
		productDao.save(product);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("userMessageSuccess", new FacesMessage("Doda³eœ przedmiot do bazy danych."));
		
		return "";
	}
	
	public String deleteProduct(String proID) {
		
		int productID = Integer.parseInt(proID);
		
		ProductDAO productDao = new ProductDAO();
		Product product = productDao.findById(productID);
		
		if(product != null) {
			productDao.delete(product);
			
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage("userMessageSuccess", new FacesMessage("Produkt zosta³ usuniêty z bazy danych."));
		}
		
		return "";
	}
	
	public String editProduct(String proID) {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			System.out.println("ProductID: " + proID);
			System.out.println("Name: " + this.getName());
			System.out.println("Price: " + this.getPrice());
			System.out.println("Category: " + this.getCategory());
			
			int productID = Integer.parseInt(proID);
			
			ProductDAO productDao = new ProductDAO();
			Product product = productDao.findById(productID);
			
			if(product != null) {
				product.setName(this.getName());
				product.setCategory(this.getCategory());
				product.setPrice(this.getPrice());
				product.setQuantity(this.getQuantity());
				
				productDao.update(product);
				
				fc.addMessage("userMessageSuccess", new FacesMessage("Produkt zosta³ zaktualizowany."));
				fc.getExternalContext().getFlash().setKeepMessages(true);
			}
			
			try {
	            FacesContext.getCurrentInstance().getExternalContext().redirect("panel.xhtml");
	         } catch (IOException e) {
	        	 
	         }
		} catch(Exception e) {
			fc.addMessage("userMessageSuccess", new FacesMessage("Nie mo¿esz usun¹æ produktu który jest w zamówieniu."));
			fc.getExternalContext().getFlash().setKeepMessages(true);
		}
		
		return "";
	}
	
	private void sortProducts() {
		
		if(this.sorting.equals("ascending") == true) {
			
			if(this.fieldSorting.equals("id") == true) {
				Collections.sort(this.productList, new IDComparatorAscending());
			} else if(this.fieldSorting.equals("price") == true) {
				Collections.sort(this.productList, new PriceComparatorAscending());
			} else if(this.fieldSorting.equals("quantity") == true) {
				Collections.sort(this.productList, new QuantityComparatorAscending());
			}
		} else {
			if(this.fieldSorting.equals("id") == true) {
				Collections.sort(this.productList, new IDComparatorDescending());
			} else if(this.fieldSorting.equals("price") == true) {
				Collections.sort(this.productList, new PriceComparatorDescending());
			} else if(this.fieldSorting.equals("quantity") == true) {
				Collections.sort(this.productList, new QuantityComparatorDescending());
			}
		}
	}
}
