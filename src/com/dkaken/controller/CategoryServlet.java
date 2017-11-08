/**
* CategoryServlet Class
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

public class CategoryServlet extends HttpServlet{

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
		
		String strId = request.getParameter("id");
		String name = request.getParameter("name");
		
		String nameAr = request.getParameter("nameAr");
		if( nameAr != null && !nameAr.equals("") ){
			byte[] bytes = nameAr.getBytes(StandardCharsets.ISO_8859_1);
			nameAr = new String(bytes, StandardCharsets.UTF_8);
		}
		if(strId == null || strId.equals("") || !Functions.isNumeric(strId)){
			response.sendRedirect("index.jsp");
		}else{
			
			int id = 0;
			if( Functions.isNumeric(strId) ){
				id = Integer.valueOf(strId);
			}
			
			Database db = (Database)request.getServletContext().getAttribute("dbConn");
			ArrayList<Item> categoryItems = Functions.getCategoryItems(db, Integer.valueOf(id));
			
			
			request.setAttribute("name", name);
			request.setAttribute("nameAr", nameAr);
			request.setAttribute("categoryItems", categoryItems);
			
			RequestDispatcher rd = request.getRequestDispatcher("categories.jsp");
			rd.forward(request, response);
		}
	}
}