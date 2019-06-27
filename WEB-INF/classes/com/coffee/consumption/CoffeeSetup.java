package com.coffee.consumption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coffee.consumption.sqlconnection.MySqlConnection;

/**
 * Servlet implementation class CoffeeSetup
 * It performs 3 tasks -
 * 		- Deletes the existing PROGRAMMERS table
 * 		- Creates a new PROGRAMMERS table
 * 		- Import data to the new PROGRAMMERS table from the text file
 */
@WebServlet("/CoffeeSetup")
public class CoffeeSetup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CoffeeSetup() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		gestionProgrammeurs.supprimerTable();
		gestionProgrammeurs.creerEtInitialiserTable();
		gestionProgrammeurs.chargerBase();
		response.getWriter().append("Successfully created table and data imported !");
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
