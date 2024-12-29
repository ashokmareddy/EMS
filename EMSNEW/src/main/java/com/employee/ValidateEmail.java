	package com.employee;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.employee.db.DataBaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ValidateEmail")
public class ValidateEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidateEmail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Boolean isEmailExists = false;

		Connection conn = null;
		CallableStatement cs = null;

		try {
			conn = DataBaseConnection.initializeDatabase();
			cs = conn.prepareCall("CALL `ems`.`getEMailInfo`(?)");
			cs.setString(1, request.getParameter("email"));

			ResultSet result = cs.executeQuery();
			if (result != null && result.next()) {
				isEmailExists = true;
			}
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

		response.getWriter().append("" + isEmailExists);
	}

}
