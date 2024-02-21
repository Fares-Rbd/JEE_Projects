package com.webapp.project.servlets;

import com.webapp.project.dao.CarDAO;
import com.webapp.project.model.Car;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CarPage")
public class CarPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CarDAO carDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        
        Connection dataSource = (Connection) getServletContext().getAttribute("db_connection");
        carDAO = new CarDAO( dataSource);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int carId = Integer.parseInt(request.getParameter("carId"));

        try {

            Car car = carDAO.getCarById(carId);

            if (car != null) {
                request.setAttribute("car", car);
                System.out.println("is this user an admin ? :"+(boolean) request.getSession().getAttribute("isAdmin"));
                if((boolean) request.getSession().getAttribute("isAdmin")) {
                    request.getRequestDispatcher("carAdmin.jsp").forward(request, response);
                	
                }
                 request.getRequestDispatcher("car.jsp").forward(request, response);
            } 
        } catch (SQLException e) {
            e.printStackTrace(); 
           
        }
    }
}
