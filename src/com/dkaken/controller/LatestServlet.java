/**
* LatestServlet Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dkaken.admin.utils.Database;
import com.dkaken.model.Item;
import com.dkaken.utils.Functions;

public class LatestServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Database db = (Database)request.getServletContext().getAttribute("dbConn");
		ArrayList<Item> items = Functions.getItems(db);
		
		request.setAttribute("items", items);
		
		RequestDispatcher rd = request.getRequestDispatcher("latest.jsp");
		rd.forward(request, response);
	}
}