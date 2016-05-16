package com.bmw.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Database {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DATABASE_ADDRESS = "jdbc:mysql://localhost/bmw_site";
	private static final String DATABASE_USER = "root";
	private static final String DATABASE_PASS = "dzik";
	
	protected Connection conn = null;
	protected Statement state = null;
	
	public void connect() { // Connect to database
		try {
			Class.forName(Database.JDBC_DRIVER); // Load driver
			//System.out.println("Connecting to database...");
			
			System.out.println("Connecting to " + DATABASE_ADDRESS);
			
			this.conn = DriverManager.getConnection(DATABASE_ADDRESS, DATABASE_USER, DATABASE_PASS); // Connecting		
			this.state = this.conn.createStatement(); // Create statement for queries
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void close() { // Close connection with database
		try {
			if(this.state != null)
				this.state.close(); // Close connection
			
			if(this.conn != null)
				this.conn.close(); // Close statement
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void closeStatement() {
		try {
			if(this.state != null)
				this.state.close();
		} catch(SQLException e) {
			System.out.println(e.toString());
		}
	}
}
