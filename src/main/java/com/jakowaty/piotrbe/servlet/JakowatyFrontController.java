package com.jakowaty.piotrbe.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JakowatyFrontController
 */
public class JakowatyFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JakowatyFrontController() {
        super();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String pathInfo = req.getPathInfo();
    	String requestMethod = req.getMethod();
    	
    	resp.getWriter().write(pathInfo);
    	resp.getWriter().write(requestMethod);
    }
}


