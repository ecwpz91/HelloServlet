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
			String value = System.getenv("ENVAR");
			String api_url = System.getenv("API_URL");
			String user_key = System.getenv("USER_KEY");

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
			if (value != null && !value.isEmpty()) {
				out.println("<p>Environment variable: " + value + "</p>");
			}

			if (user_key != null && !user_key.isEmpty()) {
				out.println("<p>User key: " + user_key + "</p>");
			}

			if (api_url != null && !api_url.isEmpty()) {
				out.println("<p>API url: " + api_url + "</p>");
			}


			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close(); // Always close the output writer
		}
	}
}
