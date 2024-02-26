package com.webapp.project.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceProvider {
    private static DataSource dataSource;

    static {
        try {
            
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");

            
            dataSource = (DataSource) envContext.lookup("jdbc/carRentalsDB");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize DataSourceProvider", e);
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
