package com.shop.controller.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.shop.model.dao.UserDAO;
import com.shop.model.dto.User;

@ManagedBean(name = "userManager")
@RequestScoped
public class UserManager {
	
	private List<User> userList;

	public UserManager() {
		
	}
	
	public List<User> getUserList() {
		
		UserDAO userDao = new UserDAO();
		this.userList = userDao.getAllUsers();
		
		return this.userList;
	}
}
