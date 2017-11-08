/**
* AdminLoginServlet Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.dkaken.admin.dao.UserDAO;
import com.dkaken.admin.dao.UserDAOImpl;
import com.dkaken.admin.model.User;
import com.dkaken.admin.utils.Database;

public class AdminLoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		request.setCharacterEncoding("UTF-8"); 
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		/*
		 ** TODO: validate the fields
		*/
		
		Database dbConn = (Database)getServletContext().getAttribute("dbConn");
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.getAdmin(dbConn, username, password);
		if(user != null){
			HttpSession session = request.getSession();
			session.setAttribute("admin_name", user.getUsername());
			session.setAttribute("admin_id", user.getId());
			response.sendRedirect("admin_dashboard.jsp");
		}else{
			response.sendRedirect("index.jsp");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request, response);
	}

}