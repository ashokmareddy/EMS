package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.employee.db.DataBaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/jsp/login.jsp").forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
				int count = 0;
				Connection conn = null;
				CallableStatement cs = null;
				boolean isLoginSuccess = false;
				Map<String,String> map = new HashMap<String,String>();
				try {
					conn = DataBaseConnection.initializeDatabase();
					cs = conn.prepareCall("CALL `ems`.`login`(?, ?)");
					cs.setString(++count, request.getParameter("email"));
					cs.setString(++count, request.getParameter("password"));
					ResultSet result = cs.executeQuery();
					
					if(result!=null && result.next()) {
						isLoginSuccess = true;
						map.put("empId", result.getString("empId"));
						map.put("name", result.getString("name"));
						map.put("email", result.getString("email"));
						map.put("phone", result.getString("phone"));
						map.put("dateOfJoining", result.getString("dateOfJoining"));
						
						
					}
					if(isLoginSuccess) {
						request.setAttribute("employee", map);
						request.getRequestDispatcher("/jsp/HomePage.jsp").forward(request, response);
					} else {
						response.setContentType("text/html");//setting the content type  
						PrintWriter pw=response.getWriter();//get the stream to write the data  
						pw.println("<html><body>");  
						pw.println("Invalid login details, Please try again!");  
						pw.println("</body></html>");  
						  
						pw.close();
					}
				} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();
				} finally {

					try {
						if (conn != null) {
							conn.close();
						}
						if (cs != null) {
							cs.close();
						}

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
	}

}
