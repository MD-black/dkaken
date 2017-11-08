/**
* TestConnectionController Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dkaken.admin.utils.Database;

public class TestConnectionController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Database dbConn = (Database)getServletContext().getAttribute("dbConn");
		Connection conn = dbConn.getConnection();
		if(conn != null){
			out.println("database is connected");
		}else{
			out.println("database is not connected");
		}
	}
}