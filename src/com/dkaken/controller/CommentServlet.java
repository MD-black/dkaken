/**
* CommentServlet Class
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
import com.dkaken.dto.CommentDTO;
import com.dkaken.model.Comment;
import com.dkaken.model.Item;
import com.dkaken.model.User;
import com.dkaken.utils.Functions;

public class CommentServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); 

		String commentVal = request.getParameter("comment");
		String strItemId = request.getParameter("itemId");
		String strUserId = String.valueOf(request.getSession().getAttribute("user_id"));
		
		int itemId = 0;
		if( Functions.isNumeric(strItemId) ){
			itemId = Integer.valueOf(strItemId);
		}
		
		int userId = 0;
		if( Functions.isNumeric(strUserId) ){
			userId = Integer.valueOf(strUserId);
		}
		
		Comment comment = new Comment();
		User user = new User();
		Item item = new Item();
		
		comment.setComment(commentVal);
		user.setId(userId);
		item.setId(itemId);
		comment.setUser(user);
		comment.setItem(item);
		
		CommentDTO commentDTO = new CommentDTO();
		ArrayList<String> errors = commentDTO.validateComment(comment);
		ItemDAO itemDAO = new ItemDAOImpl();
		CommentDAO commentDAO = new CommentDAOImpl();
		ArrayList<Comment> comments = null;
		Item requestedItem = null;
		if(errors.size() != 0){
			Database dbConn = (Database)getServletContext().getAttribute("dbConn");
			requestedItem = itemDAO.getItem(dbConn, itemId);
			if(requestedItem.getTags() != null && !requestedItem.getTags().equals("")){
				List<String> tags = Functions.getTags(requestedItem);
				request.setAttribute("tags", tags);
			}
			comments = commentDAO.getItemComments(dbConn, itemId);
			request.setAttribute("errors", errors);
			request.setAttribute("item", requestedItem);
			request.setAttribute("comments", comments);
			RequestDispatcher rd = request.getRequestDispatcher("items.jsp?id="+itemId);
			rd.forward(request, response);
		}else{
			Database dbConn = (Database)getServletContext().getAttribute("dbConn");
			requestedItem = itemDAO.getItem(dbConn, itemId);
			if(requestedItem.getTags() != null && !requestedItem.getTags().equals("")){
				List<String> tags = Functions.getTags(requestedItem);
				request.setAttribute("tags", tags);
			}
			comments = commentDAO.getItemComments(dbConn, itemId);
				
			int	addResult = commentDAO.addComment(dbConn, comment.getComment(), item, user);
			
			request.setAttribute("comments", comments);
			request.setAttribute("item", requestedItem);
			request.setAttribute("addResult", addResult);
			RequestDispatcher rd = request.getRequestDispatcher("items.jsp?id="+itemId);
			rd.forward(request, response);	
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request, response);
	}
}