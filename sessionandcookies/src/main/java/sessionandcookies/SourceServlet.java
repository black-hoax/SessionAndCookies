package sessionandcookies;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns="/SourceServlet")
public class SourceServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	// Override the doPost method to handle HTTP POST requests
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// Retrieve user credentials from the request parameters
		String username = req.getParameter("username");
		String  password = req.getParameter("password");
		// Get or create a session for the user
		HttpSession session = req.getSession();
		// Set the maximum inactive interval for the session to 60 seconds
		session.setMaxInactiveInterval(60);
		// Store the username and password in the session attributes
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		// Get a PrintWriter from the response to send HTML content
		PrintWriter out = res.getWriter();
		
		
		//redirecting to TargetServlet
		res.setContentType("text/html");
		// Output a link to the TargetServlet
		out.println("<a href='TargetServlet'> Click here to get the user name </a>");
	}
}