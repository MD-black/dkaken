/**
* ItemServlet Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dkaken.admin.utils.Database;
import com.dkaken.dao.CommentDAO;
import com.dkaken.dao.CommentDAOImpl;
import com.dkaken.dao.ItemDAO;
import com.dkaken.dao.ItemDAOImpl;
import com.dkaken.model.Comment;
import com.dkaken.model.Item;
import com.dkaken.utils.Functions;

public class ItemServlet extends HttpServlet{
	
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
		int id = 0;
		if(strId != null){
			if( Functions.isNumeric(strId) ){
				id = Integer.valueOf(strId);
			}
		}
		
		Database dbConn = (Database)getServletContext().getAttribute("dbConn");
		ItemDAO itemDAO = new ItemDAOImpl();
		Item item = itemDAO.getItem(dbConn, id);
		
		if(item != null){
			
			CommentDAO commentDAO = new CommentDAOImpl();
			ArrayList<Comment> comments = commentDAO.getItemComments(dbConn, id);
			
			for( Comment comment : comments ){
				System.out.println(comment.getComment() + ".." + comment.getUser().getUsername());
			}
			
			if(item.getTags() != null && !item.getTags().equals("")){
				List<String> tags = Functions.getTags(item);
				request.setAttribute("tags", tags);
			}
			
			request.setAttribute("comments", comments);
			request.setAttribute("item", item);
			
			RequestDispatcher rd = request.getRequestDispatcher("items.jsp");
			rd.forward(request, response);
		}else{
			Boolean isItem = true;
			isItem = false;
			
			request.setAttribute("isItem", isItem);
			
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}
}