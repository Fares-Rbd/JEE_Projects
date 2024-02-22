package com.webapp.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.*;
import java.util.*;



public class MyServlet1 extends HttpServlet
{
	private static final long serialVersionUID = 1L; 

	public void service (ServletRequest request, ServletResponse response) throws ServletException, IOException
    {
		
        response.setContentType ("text/html"); //le type du contenu de la reponse 
        PrintWriter out = response.getWriter ();
        String name = request.getParameter ("username"); //recuperation du username depuis la requete
        System.out.println ("Servlet is called");
        Locale loc = response.getLocale (); //la methode getLocale du wrapper
        out.println ("Locale set for this application is " + loc.toString ());
        
        
        out.println (name);
    }
}