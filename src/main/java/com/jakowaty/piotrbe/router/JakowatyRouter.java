package com.jakowaty.piotrbe.router;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class JakowatyRouter
{
	public GenericMapAnalyzer routeMapAnalyzer;
	
	public JakowatyRouter(InputStream routeMapFile, String mapResource) throws Exception
	{		
		this.routeMapAnalyzer = GenericMapAnalyzer.getAnalyzerByFileType(mapResource);
		
		if (!this.routeMapAnalyzer.loadStream(routeMapFile)) {
			throw new Exception("Failed to load routes");
		}
		
	}
	

	public boolean find(String method, String path, PrintWriter response)
	{
		try {
			List<?> list = this.routeMapAnalyzer.getKeys();
		} catch (Exception e) {
			response.append("EXCEPTION: " + e.getMessage());
		}
		
		return false;
	}
}
