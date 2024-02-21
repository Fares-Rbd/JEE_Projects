package com.webapp.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class loggingwrapper extends HttpServletRequestWrapper {

	public loggingwrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getParameter(String name) {
		
		return super.getParameter(name);
	}
}
