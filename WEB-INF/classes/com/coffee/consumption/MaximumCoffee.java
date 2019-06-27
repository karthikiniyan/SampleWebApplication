package com.coffee.consumption;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coffee.consumption.sqlconnection.MySqlConnection;

/**
 * Servlet implementation class MaximumCoffee
 * Computes and displays the maximum coffee consumed per day by an individual programmer
 */
@WebServlet("/MaximumCoffee")
public class MaximumCoffee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public MaximumCoffee() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tableHtml = "<table border=\"1\"><tr><th>NAME</th><th>DAYS</th><th>CUPS</th></tr>";
		try {
			Connection conn = MySqlConnection.getMySQLConnection();
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT NAME, DAYS, CUPS FROM `PROGRAMMERS` WHERE (DAYS, CUPS) IN (SELECT  DAYS, MAX(CUPS) AS MAXCUPS FROM `PROGRAMMERS` GROUP BY  DAYS) ORDER BY CUPS DESC");
			while (rs.next()) {
				tableHtml = tableHtml + "<tr><td>" + rs.getString("NAME") + "</td><td>" + rs.getString("DAYS")
						+ "</td><td>" + rs.getInt("CUPS") + "</td></tr>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().append(tableHtml + "</table>");
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
