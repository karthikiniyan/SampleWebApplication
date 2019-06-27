package com.coffee.consumption;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coffee.consumption.sqlconnection.MySqlConnection;

/**
 * Servlet implementation class FetchProgrammers Fetches coffee consumed by each
 * programmer
 */
@WebServlet("/FetchProgrammers")
public class FetchProgrammers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchProgrammers() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/coffeeByProgrammer.jsp");
		String name = request.getParameter("name");
		System.out.println(name);
		if (name != null && !name.equals("")) {
			int count = gestionProgrammeurs.nbreTotalTassesPgm(name);
			response.getWriter().append("Number of coffee consumed by " + name + " is " + count);
			return;
		}
		List<String> names = new ArrayList<String>();
		try {
			Connection conn = MySqlConnection.getMySQLConnection();

			ResultSet rs = conn.createStatement().executeQuery("SELECT DISTINCT(NAME) FROM `PROGRAMMERS` ");
			while (rs.next()) {
				names.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("names", names);
		requestDispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
