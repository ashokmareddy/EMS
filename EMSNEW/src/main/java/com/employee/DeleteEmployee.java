package com.employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.employee.db.DataBaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteEmployee
 */
@WebServlet("/DeleteEmployee")
public class DeleteEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = null;
		PreparedStatement cs = null;
		try {
			System.out.println(">>>>>>>>>>>"+Integer.parseInt(request.getParameter("empId")));
			conn = DataBaseConnection.initializeDatabase();
			cs = conn.prepareStatement("DELETE FROM employee WHERE empId="+Integer.parseInt(request.getParameter("empId")));
			cs.execute();

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
		doGet(request, response);
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getRequestDispatcher("/EMS/viewEmployee").forward(req, resp);
		resp.getWriter().append("success");
	}
}
