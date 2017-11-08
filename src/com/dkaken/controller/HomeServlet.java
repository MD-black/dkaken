/**
* HomeServlet Class
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
import com.dkaken.model.Category;
import com.dkaken.admin.utils.Database;
import com.dkaken.utils.Functions;

public class HomeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		if(action != null && action.equals("allChilds")){
			String selectedCategoryId = request.getParameter("selectedCategoryId");
			String selectedCategoryName = request.getParameter("selectedCategoryName");
			request.setAttribute("selectedCategoryId", selectedCategoryId);
			request.setAttribute("selectedCategoryName", selectedCategoryName);
			RequestDispatcher rd = request.getRequestDispatcher("childModal.jsp");
			rd.forward(request, response);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String changeLang = request.getParameter("changeLang");
		if(changeLang != null && changeLang.equals("yes")){
			String language = request.getParameter("language");
			request.getSession().setAttribute("language", language);
			response.sendRedirect("index.jsp");  
			return;
		}
			
		Database db = (Database)request.getServletContext().getAttribute("dbConn");
		
		ArrayList<Category> parentCategories = Functions.getParentCategories(db);
		
		request.setAttribute("parentCategories", parentCategories);
		
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		rd.forward(request, response);
	}
}