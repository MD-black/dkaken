/**
* TagsServlet Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dkaken.admin.utils.Database;
import com.dkaken.model.Item;
import com.dkaken.utils.Functions;

public class TagsServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String changeLang = request.getParameter("changeLang");
		if(changeLang != null && changeLang.equals("yes")){
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);  
			return;
		} 
		
		String name = request.getParameter("name");

		//good way to encode in the get method :)
		byte[] bytes = name.getBytes(StandardCharsets.ISO_8859_1);
		name = new String(bytes, StandardCharsets.UTF_8);
		
		if(name == null || name.equals("")){
			response.sendRedirect("index.jsp");
		}else{
		Database db = (Database)request.getServletContext().getAttribute("dbConn");
		ArrayList<Item> categoryItems = Functions.getCategoryItemsByTagName(db, name);
		
		request.setAttribute("name", name);
		request.setAttribute("categoryItems", categoryItems);
		
		RequestDispatcher rd = request.getRequestDispatcher("tags.jsp");
		rd.forward(request, response);
		} 
	}
}