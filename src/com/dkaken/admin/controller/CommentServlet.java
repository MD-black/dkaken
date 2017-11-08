/**
* CommentServlet Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dkaken.admin.dao.CommentDAO;
import com.dkaken.admin.dao.CommentDAOImpl;
import com.dkaken.admin.dao.ItemDAO;
import com.dkaken.admin.dao.ItemDAOImpl;
import com.dkaken.admin.dto.CommentDTO;
import com.dkaken.admin.model.Comment;
import com.dkaken.admin.model.Item;
import com.dkaken.admin.utils.Database;
import com.dkaken.admin.utils.Functions;

public class CommentServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		if(action != null){
			if(action.equals("edit")){
				Comment comment = new Comment();
				
				String commentVal = request.getParameter("comment");
				String strId = request.getParameter("id");
				int id = 0;
				if( Functions.isNumeric(strId) ){
					id = Integer.valueOf(strId);
				}
				
				comment.setId(id);
				comment.setComment(commentVal);
				
				CommentDTO commentDTO = new CommentDTO();
				ArrayList<String> errors = commentDTO.validateComment(comment, "edit");
				
				if(errors.size() != 0){
					request.setAttribute("errors", errors);
					request.setAttribute("action", "edit");
					request.setAttribute("comment", comment);
					RequestDispatcher rd = request.getRequestDispatcher("comments.jsp");
					rd.forward(request, response);
				}else{
					Database dbConn = (Database)getServletContext().getAttribute("dbConn");
					CommentDAO commentDAO = new CommentDAOImpl();
					int	editResult = commentDAO.updateComment(dbConn, comment.getId(), comment.getComment());
					request.setAttribute("editResult", editResult); 
					comment = commentDAO.getComment(dbConn, id);
					request.setAttribute("action", "edit");
					request.setAttribute("comment", comment);
					RequestDispatcher rd = request.getRequestDispatcher("comments.jsp");
					rd.forward(request, response);
				}
			}
		}else{
			request.setAttribute("action", "manage");
			
			RequestDispatcher rd = request.getRequestDispatcher("comments.jsp");
			rd.forward(request, response);
		}
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
		
		String action = request.getParameter("action");
		
		String strId = request.getParameter("id");
		int id = 0;
		if(strId != null){
			if( Functions.isNumeric(strId) ){
				id = Integer.valueOf(strId);
			}
		}
			
		if(action != null){
			if(action.equals("edit")){
				
				Database dbConn = (Database)getServletContext().getAttribute("dbConn");
				CommentDAO commentDAO = new CommentDAOImpl();
				Comment comment = commentDAO.getComment(dbConn, id);
				if(comment != null){
					request.setAttribute("comment", comment);
				}
				
				request.setAttribute("action", "edit");
				
				
				RequestDispatcher rd = request.getRequestDispatcher("comments.jsp");
				rd.forward(request, response);
			}else if(action.equals("delete")){
				
				String status = request.getParameter("status");
				String page = request.getParameter("page");
				
				Database dbConn = (Database)getServletContext().getAttribute("dbConn");
				CommentDAO commentDAO = new CommentDAOImpl();
				
				Boolean isComment = false;
				int check = 0;
				check = Functions.check(dbConn, "ID", "COMMENTS", id);
				if(check > 0){
					isComment = true;
				}
				
				Integer deleteResult = null;
				
				if(isComment){
					deleteResult = commentDAO.deleteComment(dbConn, id);
				}
				
				ArrayList<Comment> comments = null;
				if(status != null && status.equals("pending")){
					comments = commentDAO.getAllComments(dbConn, status);
				}else{
					comments = commentDAO.getAllComments(dbConn);
				}
				
				request.setAttribute("deleteResult", deleteResult);
				request.setAttribute("isComment", isComment);
				request.setAttribute("comments", comments);
				request.setAttribute("action", "manage");
				
				if(page != null && page.equals("dashboard")){
					RequestDispatcher rd = request.getRequestDispatcher("admin_dashboard.jsp");
					rd.forward(request, response);
				}else if(page != null && page.equals("items")){
					String strItemId = request.getParameter("itemId");
					
					int itemId = 0;
					if(strItemId != null){
						if( Functions.isNumeric(strItemId) ){
							itemId = Integer.valueOf(strItemId);
						}
					}
					
					ItemDAO itemDAO = new ItemDAOImpl();
					Item item = itemDAO.getItem(dbConn, itemId);
					comments = commentDAO.getItemComments(dbConn, itemId);
					
					request.setAttribute("comments", comments);
					request.setAttribute("item", item);
					request.setAttribute("action", "edit");
					RequestDispatcher rd = request.getRequestDispatcher("items.jsp");
					rd.forward(request, response);
				}else{
					RequestDispatcher rd = request.getRequestDispatcher("comments.jsp");
					rd.forward(request, response);
				}
			}else if(action.equals("approve")){
			
				String status = request.getParameter("status");
				String page = request.getParameter("page");
				
				Database dbConn = (Database)getServletContext().getAttribute("dbConn");
				CommentDAO commentDAO = new CommentDAOImpl();
				
				Boolean isComment = false;
				int check = 0;
				check = Functions.check(dbConn, "ID", "COMMENTS", id);
				if(check > 0){
					isComment = true;
				}
				
				Integer approveResult = null;
				
				if(isComment){
					approveResult = commentDAO.approveComment(dbConn, id);
				}
				ArrayList<Comment> comments = null;
				if(status != null && status.equals("pending")){
					comments = commentDAO.getAllComments(dbConn, status);
				}else{
					comments = commentDAO.getAllComments(dbConn);
				}
				
				request.setAttribute("approveResult", approveResult);
				request.setAttribute("isComment", isComment);
				request.setAttribute("comments", comments);
				request.setAttribute("action", "manage");
				
				if(page != null && page.equals("dashboard")){
					RequestDispatcher rd = request.getRequestDispatcher("admin_dashboard.jsp");
					rd.forward(request, response);
				}else if(page != null && page.equals("items")){
					String strItemId = request.getParameter("itemId");
					
					int itemId = 0;
					if(strItemId != null){
						if( Functions.isNumeric(strItemId) ){
							itemId = Integer.valueOf(strItemId);
						}
					}
					
					ItemDAO itemDAO = new ItemDAOImpl();
					Item item = itemDAO.getItem(dbConn, itemId);
					comments = commentDAO.getItemComments(dbConn, itemId);
					 
					request.setAttribute("comments", comments);
					request.setAttribute("item", item);
					request.setAttribute("action", "edit");
					RequestDispatcher rd = request.getRequestDispatcher("items.jsp");
					rd.forward(request, response);
				}else{
					RequestDispatcher rd = request.getRequestDispatcher("comments.jsp");
					rd.forward(request, response);
				}
			}
		}else{
			String status = request.getParameter("status");
			
			Database dbConn = (Database)getServletContext().getAttribute("dbConn");
			CommentDAO commentDAO = new CommentDAOImpl();
			ArrayList<Comment> comments = null;
			if(status != null && status.equals("pending")){
				comments = commentDAO.getAllComments(dbConn, status);
				request.setAttribute("status", status);
			}else{
				comments = commentDAO.getAllComments(dbConn);
			}
			
			request.setAttribute("comments", comments);
			request.setAttribute("action", "manage");
			
			RequestDispatcher rd = request.getRequestDispatcher("comments.jsp");
			rd.forward(request, response);
			
		}
		
	}
}