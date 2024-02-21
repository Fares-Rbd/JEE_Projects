package com.webapp.project.servlets;

import com.webapp.project.dao.UserDAO;
import com.webapp.project.model.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserDAO userDAO ;
	
	@Override
	public void init() throws ServletException {
		super.init();
		Connection dataSource = (Connection) getServletContext().getAttribute("db_connection");
		userDAO = new UserDAO(dataSource);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String errorMessage ;

		try {
			

			User user =userDAO.authenticateUser(username, password);
			
			if ( user!= null) {
				request.getSession().setAttribute("loggedInUser", username);
				request.getSession().setAttribute("isAdmin",user.isAdmin());
				response.sendRedirect("homepage.jsp");
			} else {
				errorMessage = "User does not exist";
				request.setAttribute("errorMessage", errorMessage);
				request.getRequestDispatcher("login.jsp").forward(request, response);

			}
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
}