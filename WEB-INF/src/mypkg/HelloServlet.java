package mypkg;

import java.io.*;
import java.util.Enumeration;

import javax.servlet.*;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// Set the response message's MIME type

		response.setContentType("text/html;charset=UTF-8");

		// Allocate a output writer to write the response message into the network
		// socket

		PrintWriter out = response.getWriter();
		

		// Write the response message, in an HTML page

		try {
			String ENVAR = System.getenv("ENVAR");
			String URI = System.getenv("URI");
			String USER_KEY = System.getenv("USER_KEY");

			out.println("<!DOCTYPE html>");
			out.println("<html><head>");
			out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
			out.println("<title>Hello, World</title></head>");
			out.println("<body>");
			out.println("<h1>HelloServlet!</h1>"); // Says HelloServlet

			out.println("<h2>Headers<h2/>");
			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				out.print("Header Name: <em>" + headerName);
				String headerValue = request.getHeader(headerName);
				out.print("</em>, Header Value: <em>" + headerValue);
				out.println("</em><br/>");
			}
			
			// Echo client's request information
			out.println("<p>Request URI: " + request.getRequestURI() + "</p>");
			out.println("<p>Protocol: " + request.getProtocol() + "</p>");
			out.println("<p>PathInfo: " + request.getPathInfo() + "</p>");
			out.println("<p>Remote Address: " + request.getRemoteAddr() + "</p>");
			// Generate a random number upon each request
			out.println("<p>A Random Number: <strong>" + Math.random() + "</strong></p>");
			
			// Check for environment variable and display if set
			if (ENVAR != null && !ENVAR.isEmpty()) {
				out.println("<p>Environment variable: " + ENVAR + "</p>");
			}

			if (USER_KEY != null && !USER_KEY.isEmpty()) {
				out.println("<p>User key: " + USER_KEY + "</p>");
			}

			if (URI != null && !URI.isEmpty()) {
				out.println("<p>API url: " + URI + "</p>");
			}


			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close(); // Always close the output writer
		}
	}
}
