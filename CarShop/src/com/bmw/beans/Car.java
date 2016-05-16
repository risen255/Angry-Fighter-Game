package com.bmw.beans;

public class Car {
	
	private int carID;
	private int userID;
	private String series;
	private String model;
	private String color;
	private int price;
	private String sourceImage;

	public Car() {
		
	}
	
	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getSourceImage() {
		return sourceImage;
	}

	public void setSourceImage(String sourceImage) {
		this.sourceImage = sourceImage;
	}
}
