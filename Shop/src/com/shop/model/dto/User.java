package com.shop.model.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
@NamedQueries({
	@NamedQuery(name = User.getByUsername, query = "SELECT u FROM User u WHERE u.username = ?1"),
	@NamedQuery(name = User.getByEmail, query = "SELECT u FROM User u WHERE u.email = ?1"),
	@NamedQuery(name = User.getAllUsers, query = "SELECT u FROM User u")
})
public class User {
	
	public static final String getByUsername = "User.getByUsername";
	public static final String getByEmail = "User.getByEmail";
	public static final String getAllUsers = "User.getAllUsers";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	private String password;
	private String email;
	private String phoneNumber;

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "ID: " + this.getId() + " Username: " + this.getUsername() + " Password: " + this.getPassword();
	}
}