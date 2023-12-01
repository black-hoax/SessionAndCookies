package sessionandcookies;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//the URL pattern ("/TargetServlet") at which it will be accessible.
@WebServlet(urlPatterns="/TargetServlet")
public class TargetServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	//This method is an override of the doGet method defined in the HttpServlet class. 
	//It is called when a client sends an HTTP request to the servlet.
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//If there is no session with req, it does not create a new one.
		HttpSession session = req.getSession(false);
		PrintWriter out = res.getWriter();
		
		
		res.setContentType("text/html");
		if(session == null) {
			//there is no current session or session expired
			out.println("session expired");}
		else {
			//The servlet retrieves the stored username and password from
			//the session and attempts to establish a connection to a MariaDB database.
				String username = (String) session.getAttribute("username");
	            String password = (String) session.getAttribute("password");
	            
	            // Attempt to establish a MariaDB connection
	            Connection connection = null;
	            
	            try {
	            	out.println("Welcome "+ username + " your login was successful!<br>");
	            	Class.forName("org.mariadb.jdbc.Driver");
	                connection = DriverManager.getConnection("jdbc:mariadb://mariadb.vamk.fi/e2102970_servlet", username, password);
	                out.println("Welcome: Your Connection to MariaDB is successful!");
	            } catch (SQLException e) {
	                // If the connection fails, redirect back to the login form
	                res.sendRedirect("user.html");
	            	e.printStackTrace();
	            } catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
	                if (connection != null) {
	                    try {
	                        connection.close();
	                    } catch (SQLException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
			}		
	}
}