package com.jakowaty.piotrbe.templates;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TemplateDispatcher {
	public TemplateDispatcher (String template, HttpServletRequest request, HttpServletResponse response) {
		request.getRequestDispatcher(template);
	}
}
