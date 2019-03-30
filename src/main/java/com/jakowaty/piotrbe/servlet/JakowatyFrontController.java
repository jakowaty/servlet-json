package com.jakowaty.piotrbe.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jakowaty.piotrbe.router.JakowatyRouter;


public class JakowatyFrontController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	/** @todo some kind of external config */
	private static final String applicationRoutesFile = "routes.json";
    private JakowatyRouter router;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JakowatyFrontController() throws Exception
    {
        super();
        
        InputStream routeMapStream = this.getClass()
        		.getClassLoader()
        		.getResourceAsStream(JakowatyFrontController.applicationRoutesFile);
        
        if (routeMapStream == null) {
        	throw new FileNotFoundException("Routing map resource not found.");
        }

        this.router = new JakowatyRouter(routeMapStream, JakowatyFrontController.applicationRoutesFile);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
    	String pathInfo = req.getPathInfo();
    	String requestMethod = req.getMethod();

		//here we know router has map analyzer initiated
		//and attempted to load stream - otherwise E would be thrown
//		resp.getWriter().append(this.router.routeMapAnalyzer.asString);
		
		resp.getWriter().append(this.router.routeMapAnalyzer.asString);
    	if (this.router.find(requestMethod, pathInfo, resp.getWriter())) {
    		
    	}
    }
}