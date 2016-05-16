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


@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Register() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		if(request.getParameter("registerUserSubmit") != null) {
			
			DatabaseBMW db = new DatabaseBMW();
			db.connect(); // Open connection
			
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String email = request.getParameter("email");
			
			System.out.println(login);
			System.out.println(password);
			System.out.println(name);
			System.out.println(surname);
			System.out.println(email);
			
			if(login.length() > 0) {
				if(password.length() >= 8) {
					if(name.length() > 0) {
						if(surname.length() > 0) {
							if(email.length() > 0 || email.contains("@") == true) {
								if(db.isLoginBusy(login) == false) {
									if(db.isEmailBusy(login) == false) {
										User user = new User(); // Create new instance of user
										user.setLogin(login);
										user.setPassword(password);
										user.setName(name);
										user.setSurname(surname);
										user.setEmail(email);
										
										if(db.registerUser(user) == true) {
											int userID = db.getUserID(user.getLogin());
											user.setUserID(userID);
											user.setPassword(""); // Clear user password
											
											// Synchronized session
											HttpSession session = request.getSession();
											synchronized(session) {
												session.setAttribute("user", user); // Set user object with all atributes
											}
											
											request.setAttribute("registerUserMsg", "REGISTERED");
										} else {
											request.setAttribute("registerUserMsg", "WRONG_ADD_RECORD_TO_DATABASE");
										}
									} else {
										request.setAttribute("registerUserMsg", "EMAIL_BUSY");
									}
								} else {							 
									request.setAttribute("registerUserMsg", "LOGIN_BUSY");
								}
							} else {
								request.setAttribute("registerUserMsg", "INCORRECT_EMAIL");
							}
						} else {
							request.setAttribute("registerUserMsg", "FIELD_SURNAME_EMPTY");
						}
					} else {
						request.setAttribute("registerUserMsg", "FIELD_NAME_EMPTY");
					}
				} else {
					request.setAttribute("registerUserMsg", "PASSWORD_TOO_SHORT");
				}
			} else {
				request.setAttribute("registerUserMsg", "FIELD_LOGIN_EMPTY");
			}
			
			db.close(); // Close connection with database
		}
		
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}
}
