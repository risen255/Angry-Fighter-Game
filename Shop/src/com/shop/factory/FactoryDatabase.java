package com.shop.factory;

import com.shop.model.dao.ProductDAO;
import com.shop.model.dao.PurchaseDAO;
import com.shop.model.dao.UserDAO;
import com.shop.model.dto.Product;
import com.shop.model.dto.Purchase;
import com.shop.model.dto.User;

public class FactoryDatabase {
	
	public static void createTables() {
		
		UserDAO userDao = new UserDAO();
		ProductDAO productDao = new ProductDAO();
		PurchaseDAO purchaseDao = new PurchaseDAO();
		
		User user = new User();
		user.setUsername("risen255");
		user.setPassword("rafal12345");
		user.setEmail("risen255@gmail.com");
		user.setPhoneNumber("999999999");
		
		userDao.save(user);
		
		Product pro1 = new Product();
		pro1.setName("Monitor");
		pro1.setCategory("Elektronika");
		pro1.setPrice(500);
		pro1.setQuantity(1700);
		
		/*Product pro2 = new Product();
		pro2.setName("Tymbark");
		pro2.setCategory("Napoje");
		pro2.setPrice(3);
		pro2.setQuantity(2500);
		
		Product pro3 = new Product();
		pro3.setName("Szafka");
		pro3.setCategory("Meble");
		pro3.setPrice(100);
		pro3.setQuantity(400);*/
		
		productDao.save(pro1);
		//productDao.save(pro2);
		//productDao.save(pro3);

		/*Purchase pur1 = new Purchase();
		pur1.setUser(user);
		pur1.setProduct(pro2);
		pur1.setQuantity(12);
		pur1.setRealized("Tak");*/
		
		Purchase pur2 = new Purchase();
		pur2.setUser(user);
		pur2.setProduct(pro1);
		pur2.setQuantity(15);
		pur2.setRealized("Nie");
		
		/*Purchase pur3 = new Purchase();
		pur3.setUser(user);
		pur3.setProduct(pro3);
		pur3.setQuantity(24);
		pur3.setRealized("Tak");*/
		
		//purchaseDao.save(pur1);
		purchaseDao.save(pur2);
		//purchaseDao.save(pur3);
	}
}
