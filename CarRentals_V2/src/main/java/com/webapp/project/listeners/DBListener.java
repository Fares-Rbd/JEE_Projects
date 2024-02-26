package com.webapp.project.listeners;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class DBListener implements ServletContextListener {

    private Connection connection;
	public static final String DB_CONNECTION= "db_connection";
	
	
    public DBListener() {
    }
    public void contextInitialized(ServletContextEvent sce)  { 
    	InitialContext ic;
        try {
            ic = new InitialContext();
            DataSource ds;
            ds = (DataSource) ic.lookup("java:comp/env/jdbc/carRentalsDB");
            connection = ds.getConnection();
            sce.getServletContext().setAttribute(DB_CONNECTION, connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void contextDestroyed(ServletContextEvent sce)  { 
    	 if (connection != null) {
             try {
                 connection.close();
             } catch (SQLException e) {
                 throw new IllegalStateException("Error closing the database connection", e);
             }
         }
    }
	
}
