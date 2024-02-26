package com.webapp.project.filters;



import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.webapp.project.wrappers.LoggingWrapper;

@WebFilter(urlPatterns = {"/*", "/"})
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Log request
        LoggingWrapper.logRequest(httpRequest.getMethod(), httpRequest.getRequestURI());

        // Proceed with the request
        chain.doFilter(request, response);

        // Log response
        LoggingWrapper.logResponse(httpResponse.getStatus());
    }

  
}
