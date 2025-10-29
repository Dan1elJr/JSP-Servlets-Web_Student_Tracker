package com.sandaniel.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	//Define datasource/connnection pool for Resource Injection
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Step 1: Set up the printWriter
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		//Step 2: Get a connection to the database
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection(); // Connection factory / return a connection to database
			//Step 3: Create a SQL statements
			
			
			myStmt = myConn.createStatement(); // Connection statement / return a statement to execute SQL to database
			String sql = "SELECT * FROM student"; // SQL command
			
			//Step 4: Execute SQL query
			myRs = myStmt.executeQuery(sql); //Statement / Executing SQL command and return their results
			
			//Step 5: Process the result set
			
			while (myRs.next()) { // While - iteration inside results
				
				String email = myRs.getString("email"); // getting unique field called "email"
				out.println("<p>"+email+"</p>"); // showing email field value
				
			}
			
		}
		catch(Exception exc) {
			exc.printStackTrace();
			
		}
	}

}
