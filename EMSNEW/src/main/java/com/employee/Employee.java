package com.employee;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.employee.db.DataBaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Employee
 */
@WebServlet("/employee")
public class Employee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/jsp/EmployeeSuccess.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ename   = " + request.getParameter("ename"));
		System.out.println("email   = " + request.getParameter("email"));
		System.out.println("phone   = " + request.getParameter("phone"));
		System.out.println("doj   = " + request.getParameter("doj"));
		String errorMessage = "";
		if (null == request.getParameter("ename") || "".equals(request.getParameter("ename"))) {
			errorMessage += "Invalid Employee Name";
		} else if (null == request.getParameter("email") || "".equals(request.getParameter("email"))) {
			errorMessage += "Invalid Employee Email";
		} else if (null == request.getParameter("phone") || "".equals(request.getParameter("phone"))) {
			errorMessage += "Invalid Employee Phone";
		} else if (null == request.getParameter("doj") || "".equals(request.getParameter("doj"))) {
			errorMessage += "Invalid Employee Date Of joining";
		} else if (null == request.getParameter("password") || "".equals(request.getParameter("password"))) {
			errorMessage += "Password field can not be null";
		}

		if (!errorMessage.equals("")) {

			//response.getWriter().append(errorMessage);
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("/").forward(request, response);
		}
		String returnValue = "@Data";
		int count = 0;
		Connection conn = null;
		CallableStatement cs = null;

		try {
			conn = DataBaseConnection.initializeDatabase();
			cs = conn.prepareCall("CALL `ems`.`addEmployee`(?, ?, ?,?,?,?)");
			cs.setString(++count, request.getParameter("ename"));
			cs.setString(++count, request.getParameter("email"));
			cs.setLong(++count, Long.parseLong(request.getParameter("phone")));
			LocalDate date = LocalDate.parse(request.getParameter("doj"), formatter);
			cs.setString(++count, request.getParameter("doj"));
			cs.setString(++count, request.getParameter("password"));
			cs.setString(++count, returnValue);

			cs.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.getMessage();
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
		doGet(request, response);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
		String returnValue = "@Data";
		int count = 0;
		Connection conn = null;
		CallableStatement cs = null;

		try {
			conn = DataBaseConnection.initializeDatabase();
			
			

			cs = conn.prepareCall("CALL updateEmployee(?,?,?,?)");
			cs.setString(++count, req.getParameter("empId"));
			cs.setString(++count, req.getParameter("ename"));
			cs.setLong(++count, Long.parseLong(req.getParameter("phone")));
			cs.setString(++count, req.getParameter("dateOfJoining"));
			cs.setString(++count, returnValue);

			cs.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.getMessage();
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
		
		resp.sendRedirect("/EMSNEW/viewEmployee");
	}

}
