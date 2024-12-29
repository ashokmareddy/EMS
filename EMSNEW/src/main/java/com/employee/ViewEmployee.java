package com.employee;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.employee.db.DataBaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewEmployee
 */
@WebServlet("/viewEmployee")
public class ViewEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = null;
		CallableStatement cs = null;
		List<Map<String, String>> empList =new ArrayList<>();
		Map<String, String> map = new HashMap<String, String>();
		try {
			conn = DataBaseConnection.initializeDatabase();
			cs = conn.prepareCall("CALL `ems`.`listEmployess`()");
			ResultSet result = cs.executeQuery();

			while (result != null && result.next()) {
				map = new HashMap<String, String>();
				map.put("empId", result.getString("empId"));
				map.put("name", result.getString("name"));
				map.put("email", result.getString("email"));
				map.put("phone", result.getString("phone"));
				map.put("dateOfJoining", result.getString("dateOfJoining"));
				empList.add(map);
			}
				request.setAttribute("employeeList", empList);
				
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

		request.getRequestDispatcher("/jsp/EmployeeList.jsp").forward(request, response);
	}

}
