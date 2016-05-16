package com.shop.model.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
@Table(name = "PURCHASE")
@NamedQueries({
	@NamedQuery(name = Purchase.getAllPurchases, query = "SELECT pur FROM Purchase pur")
})
public class Purchase {
	
	public static final String getAllPurchases = "Product.getAllPurchases";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@CascadeOnDelete
	private Product product;
	
	private int quantity;
	
	private String realized;
	
	public Purchase() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRealized() {
		return realized;
	}

	public void setRealized(String realized) {
		this.realized = realized;
	}
}