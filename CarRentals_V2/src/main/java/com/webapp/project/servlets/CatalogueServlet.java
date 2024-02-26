package com.webapp.project.servlets;

import java.io.IOException;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//import com.webapp.project.dao.CarDAO;
//import com.webapp.project.model.Car;

@WebServlet("/Catalogue")
public class CatalogueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			request.getRequestDispatcher("/catalogue.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error	AAAAAAAAAAAAAAAAA");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}