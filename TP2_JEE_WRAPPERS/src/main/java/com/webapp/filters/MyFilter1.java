package com.webapp.filters;

import java.io.*;

import com.webapp.wrappers.RequestWrapper1;
import com.webapp.wrappers.ResponseWrapper1;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;


public class MyFilter1 implements Filter {
	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

	// This method is called each time a client requests for a web resource i.e.
	// preprocessing request
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		
		//appel du request wrapper
		out.println("<b> <i>Filtering request and passing it to Request Wrapper class</i> </b> <br/>");

		// Calling the constructor of request wrapper class
		RequestWrapper1 requestWrapper = new RequestWrapper1(request);


		out.println("<b> <i>Filter is filtering the response and passing it to Wrapper class</i> </b> <br/>");

		// appel du response wrapper
		// Calling the constructor of response wrapper class
		ResponseWrapper1 responseWrapper = new ResponseWrapper1(response);

		// This method calls the next filter in the chain
		chain.doFilter(requestWrapper, responseWrapper); //envoie de la requete & reponse apres le traitement du Request & Response wrappers

	}
}
