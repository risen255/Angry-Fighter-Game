package com.bmw.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.bmw.beans.Car;
import com.bmw.database.DatabaseBMW;
import com.bmw.filestream.FileStream;
import com.bmw.servlets.extensions.SeriesExtension;
import com.bmw.shared.Helper;

@WebServlet("/Series5")
@MultipartConfig
public class Series5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Series5() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SeriesExtension se = new SeriesExtension();
		se.loadCars(request, response, "5");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SeriesExtension se = new SeriesExtension();
		se.processSeries(request, response, "5");
	}
}
