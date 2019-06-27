package com.coffee.consumption;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coffee.consumption.sqlconnection.MySqlConnection;

/**
 * Servlet implementation class CustomQuery
 * Executes a query written in front end text area
 * Appends the output of the query and also its metadata to HttpRequest and forwards the output
 */
@WebServlet("/CustomQuery")
public class CustomQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("query");
		System.out.println(query);
		if(query != null && !query.equals(""))
		{
			System.out.println(query);
			Connection con = MySqlConnection.getMySQLConnection();
			try {
				ResultSet rs  = con.createStatement().executeQuery(query);
				ResultSetMetaData rsmd = rs.getMetaData();
				ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
				ArrayList<String> header = new ArrayList<String>();
				int columnCount = rsmd.getColumnCount();
				HashMap<String, String> metaData = new HashMap<String,String>();
				for(int i = 1; i <= columnCount ; i ++)
				{
					header.add(rsmd.getColumnLabel(i));
					metaData.put(rsmd.getColumnName(i),  rsmd.getColumnTypeName(i));
				}
				list.add(header);
				while(rs.next())
				{
					ArrayList<String> internal = new ArrayList<String>();
					for(int i = 1; i <= columnCount ; i ++)
					{
						internal.add(rs.getString(i));
					}
					list.add(internal);
				}
				request.setAttribute("result", list);
				request.setAttribute("meta", metaData);
				RequestDispatcher rd = request.getRequestDispatcher("/customQuery.jsp");
				rd.forward(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().append("Exception occured with your query - " + e.getMessage());
			}
			response.getWriter().append("Couldn't execute query now !");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
