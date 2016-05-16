package com.bmw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmw.database.DatabaseBMW;
import com.bmw.servlets.extensions.SeriesExtension;

@WebServlet("/Series1")
@MultipartConfig
public class Series1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Series1() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SeriesExtension se = new SeriesExtension();
		se.loadCars(request, response, "1");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SeriesExtension se = new SeriesExtension();
		se.processSeries(request, response, "1");
	}
}
