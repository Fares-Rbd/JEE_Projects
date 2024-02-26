package com.webapp.project.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CarPage")
public class CarPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String carId = request.getParameter("carId");
		request.getSession().setAttribute("carId", carId);

		if ((boolean) request.getSession().getAttribute("isAdmin"))
			request.getRequestDispatcher("carAdmin.jsp").forward(request, response);

		else
			request.getRequestDispatcher("car.jsp").forward(request, response);
	}
}
