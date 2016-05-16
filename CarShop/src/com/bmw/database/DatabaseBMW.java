package com.bmw.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bmw.beans.Car;
import com.bmw.beans.User;
import com.bmw.shared.Helper;

public class DatabaseBMW extends Database {
	
	public DatabaseBMW() {
		super();
	}
	
	public ArrayList<Car> getCars(String series) {
		
		ArrayList<Car> carList = new ArrayList<Car>();
		
		try {
			ResultSet rs = this.state.executeQuery("SELECT * FROM cars WHERE series='" + series + "'"); // Execute sql with specified series of bmw
			
			while(rs.next() == true) {
				
				//Create bean car
				Car car = new Car();
				car.setCarID(rs.getInt("id_car"));
				car.setSeries(rs.getString("series"));
				car.setModel(rs.getString("model"));
				car.setColor(rs.getString("color"));
				car.setPrice(rs.getInt("price"));
				car.setSourceImage(rs.getString("source_image"));			
				
				//Add car to list
				carList.add(car);
			}
		} catch(SQLException e) {
			System.out.println(e.toString());
		}
		
		return carList;
	}
	
	public boolean saveCar(Car car) {
		
		boolean saved = false;
		
		try {
			
			String sql = "INSERT INTO cars (series, model, color, price, source_image) VALUES('" +
						  car.getSeries() + "', '" +
						  car.getModel() + "', '" + 
						  car.getColor() + "', '" + 
						  car.getPrice() + "', '" + 
						  car.getSourceImage() + "')";
			System.out.println(sql);
			
			if(this.state.executeUpdate(sql) > 0)
				saved = true;
			
		} catch(SQLException e) {
			System.out.println(e.toString());
		}
		
		return saved;
	}
	
	public boolean deleteCar(int carID) {
		
		boolean deleted = false;
		
		try {
			String sql = "DELETE FROM cars WHERE id_car='" + carID + "'";
			System.out.println(sql);
			
			if(this.state.executeUpdate(sql) > 0)
				deleted = true;
				
		} catch(SQLException e) {
			System.out.println(e.toString());
		}
		
		return deleted;
	}
	
	public boolean isCarExist(String series, String model) {

		boolean exist = false;
		
		try {
			String sql = "SELECT id_car FROM cars WHERE series='" + series + "' AND model='" + model + "'";
			System.out.println(sql);
			
			ResultSet rs = this.state.executeQuery(sql);
			if(rs.next() == true)
				exist = true;
			
		} catch(SQLException e) {
			System.out.println(e.toString());
		}
		
		return exist;
	}
	
	public boolean isLoginBusy(String login) {
		
		boolean busy = false;
		
		try {
			String sql = "SELECT user_id FROM users WHERE login='" + login + "'";
			System.out.println(sql);
			
			ResultSet rs = this.state.executeQuery(sql);
			if(rs.next() == true)
				busy = true;
			
		} catch(SQLException e) {
			System.out.println(e.toString());
		}
		
		return busy;
	}
	
	public boolean isEmailBusy(String email) {
		
		boolean busy = false;
		
		try {
			String sql = "SELECT user_id FROM users WHERE email='" + email + "'";
			System.out.println(sql);
			
			ResultSet rs = this.state.executeQuery(sql);
			if(rs.next() == true)
				busy = true;
			
		} catch(SQLException e) {
			System.out.println(e.toString());
		}
		
		return busy;
	}
	
	public boolean registerUser(User user) {
		
		boolean registered = false;
		
		try {			
			String sql = "INSERT INTO users (login, password, name, surname, email) VALUES('" +
						  user.getLogin() + "', '" +
						  Helper.md5(user.getPassword()) + "', '" +
						  user.getName() + "', '" +
						  user.getSurname() + "', '" +
						  user.getEmail() + "')";		
			System.out.println(sql);
			
			if(this.state.executeUpdate(sql) > 0)
				registered = true;
					
		} catch(SQLException e) {
			System.out.println(e.toString());
		}
		
		return registered;
	}

	public int getUserID(String login) {
		
		int userID = -1;
		
		try {
			String sql = "SELECT user_id FROM users WHERE login='" + login + "'";
			System.out.println(sql);
			
			ResultSet rs = this.state.executeQuery(sql);
			if(rs.next() == true)
				userID = rs.getInt("user_id");
			
		} catch(SQLException e) {
			System.out.println(e.toString());
		}
		
		return userID;
	}
	
	public boolean loginToAccount(String login, String password) {
		
		boolean logged = false;
		
		try {
			String sql = "SELECT user_id FROM users WHERE login='" + login + "' AND password='" + Helper.md5(password) + "'";
			System.out.println(sql);
			
			ResultSet rs = this.state.executeQuery(sql);
			if(rs.next() == true)
				logged = true;
				
		} catch(SQLException e) {
			System.out.println(e.toString());
		}
		
		return logged;
	}
	
	public User getUser(String login) {
		User user = null;
		
		try {
			String sql = "SELECT user_id, name, surname, money FROM users WHERE login='" + login + "'";
			System.out.println(sql);
			
			ResultSet rs = this.state.executeQuery(sql);
			if(rs.next() == true) {
				user = new User();
				user.setUserID(rs.getInt("user_id"));
				user.setLogin(login);
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setMoney(rs.getInt("money"));
			}
			
		} catch(SQLException e) {
			System.out.println(e.toString());
		}
		
		return user;
	}
}
