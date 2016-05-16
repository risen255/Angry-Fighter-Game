package com.bmw.servlets.extensions;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmw.beans.Car;
import com.bmw.database.DatabaseBMW;
import com.bmw.filestream.FileStream;
import com.bmw.shared.Helper;

public class SeriesExtension {
	
	public SeriesExtension() {
	}
	
	public void loadCars(HttpServletRequest request, HttpServletResponse response, String series) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		DatabaseBMW db = new DatabaseBMW();
		db.connect(); // Open connection
		request.setAttribute("bmwSeries", series); // Necessary to identify series of bmw
		request.setAttribute("bmwCars", db.getCars(series)); // Get cars to show them on the page
		db.close(); // Close DB
		
		request.getRequestDispatcher("series.jsp").forward(request, response);
	}

	public void processSeries(HttpServletRequest request, HttpServletResponse response, String series) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		DatabaseBMW db = new DatabaseBMW();
		db.connect(); // Open connection
		
		if(request.getParameter("uploadSubmit") != null) { // Upload submit was clicked
			FileStream fs = new FileStream();
			String fileName = fs.getFileFromRequest(request);
			if(fs.isJPG(fileName) == true || fs.isPNG(fileName) == true) {
				Random rand = new Random(); // Object to count random number
				String sourceImage = null; // Source image for database
				String fileNameMd5 = Helper.md5(Integer.toString(rand.nextInt(999999999))); // Get md5 file name for database
				
				if(fs.isJPG(fileName) == true)
					sourceImage = fileNameMd5 + ".jpg";
				else if(fs.isPNG(fileName) == true)
					sourceImage = fileNameMd5 + ".png";
				
				String model = request.getParameter("model");
				String color = request.getParameter("color");
				String priceStr = request.getParameter("price");
				
				System.out.println(model);
				System.out.println(color);
				System.out.println(priceStr);
				
				if(model.length() > 0) {
					if(color.length() > 0) {
						if(priceStr.length() > 0) {
							if(Helper.isNumeric(priceStr) == true) {
								int price = Integer.parseInt(priceStr);
								if(price > 0) {
									if(db.isCarExist(series, model) == false) {
										Car car = new Car(); // Create new instance of car
										car.setSeries(series);
										car.setModel(model);
										car.setColor(color);
										car.setPrice(price);
										car.setSourceImage(sourceImage);
										
										System.out.println("sourceImage: " + sourceImage);
										
										if(db.saveCar(car) == true) { // Save car to DB									
											fs.saveFile("/images/bmw_series/" + series + "/", sourceImage);
											request.setAttribute("loadCarMsg", "SAVED"); // Car successfully saved
										} else {
											request.setAttribute("loadCarMsg", "WRONG_ADD_RECORD_TO_DATABASE");
										}
									} else {
										request.setAttribute("loadCarMsg", "CAR_ALREADY_EXISTS");
									}
								} else {
									request.setAttribute("loadCarMsg", "PRICE_CAN_NOT_BE_ZERO");
								}
							} else {
								request.setAttribute("loadCarMsg", "PRICE_MUST_BE_NUMERIC");
							}
						} else {
							request.setAttribute("loadCarMsg", "PRICE_SHORT_LENGTH");
						}
					} else {
						request.setAttribute("loadCarMsg", "COLOR_SHORT_LENGTH");
					}
				} else {
					request.setAttribute("loadCarMsg", "MODEL_SHORT_LENGTH");
				}
				
			} else { 
				request.setAttribute("loadCarMsg", "BAD_FORMAT"); // File is in bad format
			}
				
			fs.close(); // Close file stream
		}
		else if(request.getParameter("deleteCarSubmit") != null) { // Delete car submit was clicked
			int carID = Integer.parseInt(request.getParameter("carID"));
			
			if(db.deleteCar(carID) == true)
				request.setAttribute("deleteCarMsg", "DELETED");
			else
				request.setAttribute("deleteCarMsg", "CAN_NOT_DELETE");
		}
	
		request.setAttribute("bmwSeries", series); // Necessary to identify series of bmw
		request.setAttribute("bmwCars", db.getCars(series)); // Get cars to show them on the page
		db.close(); // Close DB
		
		request.getRequestDispatcher("series.jsp").forward(request, response);
	}
}
