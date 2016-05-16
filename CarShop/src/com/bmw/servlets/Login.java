package com.bmw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bmw.beans.User;
import com.bmw.database.DatabaseBMW;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		if(request.getParameter("loginUserSubmit") != null) { // Login user button was clicked
			
			DatabaseBMW db = new DatabaseBMW();
			db.connect(); // Connect to database
			
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			
			if(db.loginToAccount(login, password) == true) { // Logged in successfully
				User user = db.getUser(login); // Get information about user
			
				// Synchronized session
				HttpSession session = request.getSession();
				synchronized(session) {
					session.setAttribute("user", user);
				}
				request.setAttribute("loginUserMsg", "LOGGED");
				
				System.out.println("SESSSION: " + ((User)request.getSession().getAttribute("user")).getLogin());
			} else { // Incorrert login or password
				request.setAttribute("loginUserMsg", "INCORRECT_LOGIN_OR_PASSWORD");
			}
			
			System.out.println(login);
			System.out.println(password);
			
			db.close();
		}
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
}
