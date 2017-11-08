/**
* ItemServlet Class
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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.dkaken.admin.dao.CategoryDAO;
import com.dkaken.admin.dao.CategoryDAOImpl;
import com.dkaken.admin.dao.CommentDAO;
import com.dkaken.admin.dao.CommentDAOImpl;
import com.dkaken.admin.dao.ItemDAO;
import com.dkaken.admin.dao.ItemDAOImpl;
import com.dkaken.admin.dao.UserDAO;
import com.dkaken.admin.dao.UserDAOImpl;
import com.dkaken.admin.dto.ItemDTO;
import com.dkaken.admin.model.Category;
import com.dkaken.admin.model.Comment;
import com.dkaken.admin.model.Item;
import com.dkaken.admin.model.User;
import com.dkaken.admin.utils.Database;
import com.dkaken.admin.utils.Functions;

@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
		maxFileSize=1024*1024*10,      // 10MB
		maxRequestSize=1024*1024*50)   // 50MB
public class ItemServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		
		if(action != null){
			if(action.equals("edit")){
				Item item = new Item();
				User user = new User();
				Category category = new Category();

				String name = request.getParameter("name");
				String description = request.getParameter("description");
				String price = request.getParameter("price");
				String madeIn = request.getParameter("madeIn");
				String status = request.getParameter("status");
				String strUserId = request.getParameter("user");
				String strCategoryId = request.getParameter("category");
				String strId = request.getParameter("id");
				String tags = request.getParameter("tags");
				Part part = request.getPart("image");
				
				int id = 0;
				int userId = 0;
				int categoryId = 0;
				if( Functions.isNumeric(strId) ){
					id = Integer.valueOf(strId);
				}
				if( Functions.isNumeric(strUserId) ){
					userId = Integer.valueOf(strUserId);
				}
				if( Functions.isNumeric(strCategoryId) ){
					categoryId = Integer.valueOf(strCategoryId);
				}
				
				item.setId(id);
				item.setName(name);
				item.setDescription(description);
				item.setPrice(price);
				item.setMadeIn(madeIn);
				item.setStatus(status);
				user.setId(userId);
				item.setUser(user);
				category.setId(categoryId);
				item.setCategory(category);
				item.setTags(tags);
				
				ItemDTO itemDTO = new ItemDTO();
				ArrayList<String> errors = itemDTO.validateItem(item, "edit");
				ArrayList<String> partErrors = Functions.validatePart(part);
				errors.addAll(partErrors);
				
				if(errors.size() != 0){
					request.setAttribute("errors", errors);
					request.setAttribute("action", "edit");
					request.setAttribute("item", item);
					Database dbConn = (Database)getServletContext().getAttribute("dbConn");
					UserDAO userDAO = new UserDAOImpl();
					CategoryDAO categoryDAO = new CategoryDAOImpl();
					ArrayList<User> users =  userDAO.getAllUsers(dbConn);
					ArrayList<Category> categories = categoryDAO.getAllCategories(dbConn, "asc");
					request.setAttribute("users", users);
					request.setAttribute("categories", categories);
					RequestDispatcher rd = request.getRequestDispatcher("items.jsp");
					rd.forward(request, response);
				}else{
					String fileName = Functions.uploadItemPic(part);
					
					Database dbConn = (Database)getServletContext().getAttribute("dbConn");
					ItemDAO itemDAO = new ItemDAOImpl();
					int	editResult = itemDAO.updateItem(dbConn,
												item.getId(),
												item.getName(),
												item.getDescription(),
												item.getPrice(),
												item.getMadeIn(),
												item.getStatus(),
												item.getUser(),
												item.getCategory(),
												item.getTags(),
												fileName);
					request.setAttribute("editResult", editResult);
					item = itemDAO.getItem(dbConn, id);
					UserDAO userDAO = new UserDAOImpl();
					CategoryDAO categoryDAO = new CategoryDAOImpl();
					ArrayList<User> users =  userDAO.getAllUsers(dbConn);
					ArrayList<Category> categories = categoryDAO.getAllCategories(dbConn, "asc");
					
					request.setAttribute("item", item);
					request.setAttribute("users", users);
					request.setAttribute("categories", categories);
					
					request.setAttribute("action", "edit");
					
					RequestDispatcher rd = request.getRequestDispatcher("items.jsp");
					rd.forward(request, response);
				}
			}else if(action.equals("add")){
				Item item = new Item();
				Category category = new Category();
				User user = new User();
				
				String name = request.getParameter("name");
				String description = request.getParameter("description");
				String price = request.getParameter("price");
				String madeIn = request.getParameter("madeIn");
				String status = request.getParameter("status");
				String userId = request.getParameter("user");
				String catId = request.getParameter("category");
				String tags = request.getParameter("tags");
				Part part = request.getPart("image");
				
				System.out.println(tags);
				
				item.setName(name);
				item.setDescription(description);
				item.setPrice(price);
				item.setMadeIn(madeIn);
				item.setStatus(status);
				user.setId(Integer.valueOf(userId));
				item.setUser(user);
				category.setId(Integer.valueOf(catId));
				item.setCategory(category);
				item.setTags(tags);
				
				ItemDTO itemDTO = new ItemDTO();
				ArrayList<String> errors = itemDTO.validateItem(item, "add");
				ArrayList<String> partErrors = Functions.validatePart(part);
				errors.addAll(partErrors);
				
				if(errors.size() != 0){
					request.setAttribute("errors", errors);
					
					Database dbConn = (Database)getServletContext().getAttribute("dbConn");
					UserDAO userDAO = new UserDAOImpl();
					CategoryDAO categoryDAO = new CategoryDAOImpl();
					ArrayList<User> users =  userDAO.getAllUsers(dbConn);
					ArrayList<Category> categories = categoryDAO.getAllCategories(dbConn, "asc");
					request.setAttribute("users", users);
					request.setAttribute("categories", categories);
					
					request.setAttribute("action", "add");
					RequestDispatcher rd = request.getRequestDispatcher("items.jsp");
					rd.forward(request, response);
				}else{
					String fileName = Functions.uploadItemPic(part);
					
					Database dbConn = (Database)getServletContext().getAttribute("dbConn");
					ItemDAO itemDAO = null;
					ArrayList<Item> items = null; 
					int addResult = 0;
					itemDAO = new ItemDAOImpl();
					addResult = itemDAO.addItem(dbConn,
												item.getName(),
												item.getDescription(),
												item.getPrice(),
												item.getMadeIn(),
												item.getStatus(),
												item.getUser(),
												item.getCategory(),
												item.getTags(),
												fileName);
					request.setAttribute("addResult", addResult);
					items = itemDAO.getAllItems(dbConn);
					request.setAttribute("items", items);
					request.setAttribute("action", "manage");
					RequestDispatcher rd = request.getRequestDispatcher("items.jsp");
					rd.forward(request, response);
					
				}
			}
		}else{
			request.setAttribute("action", "manage");
			
			RequestDispatcher rd = request.getRequestDispatcher("items.jsp");
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
				ItemDAO itemDAO = new ItemDAOImpl();
				Item item = itemDAO.getItem(dbConn, id);
				if(item != null){
					request.setAttribute("item", item);
					
					UserDAO userDAO = new UserDAOImpl();
					CategoryDAO categoryDAO = new CategoryDAOImpl();
					CommentDAO commentDAO = new CommentDAOImpl();
					ArrayList<User> users =  userDAO.getAllUsers(dbConn);
					ArrayList<Category> categories = categoryDAO.getAllCategories(dbConn, "asc");
					ArrayList<Comment> comments = commentDAO.getItemComments(dbConn, id); 
					request.setAttribute("users", users);
					request.setAttribute("categories", categories);
					request.setAttribute("comments", comments);
				}
				
				request.setAttribute("action", "edit");
				
				RequestDispatcher rd = request.getRequestDispatcher("items.jsp");
				rd.forward(request, response);
			}else if(action.equals("add")){
				Database dbConn = (Database)getServletContext().getAttribute("dbConn");
				
				UserDAO userDAO = new UserDAOImpl();
				CategoryDAO categoryDAO = new CategoryDAOImpl();
				
				ArrayList<User> users =  userDAO.getAllUsers(dbConn);
				ArrayList<Category> categories = categoryDAO.getAllCategories(dbConn, "asc");
				
				request.setAttribute("users", users);
				request.setAttribute("categories", categories);
				request.setAttribute("action", "add");
				RequestDispatcher rd = request.getRequestDispatcher("items.jsp");
				rd.forward(request, response);
			}else if(action.equals("delete")){
				String status = request.getParameter("status");
				String page = request.getParameter("page");
				
				Database dbConn = (Database)getServletContext().getAttribute("dbConn");
				ItemDAO itemDAO = new ItemDAOImpl();
				
				Boolean isItem = false;
				int check = 0;
				check = Functions.check(dbConn, "ID", "ITEMS", id);
				if(check > 0){
					isItem = true;
				}
				
				Integer deleteResult = null;
				
				if(isItem){
					deleteResult = itemDAO.deleteItem(dbConn, id);
				}
				
				ArrayList<Item> items = null;
				if(status != null && status.equals("pending")){
					items = itemDAO.getAllItems(dbConn, status);
				}else{
					items = itemDAO.getAllItems(dbConn);
				}
				
				request.setAttribute("deleteResult", deleteResult);
				request.setAttribute("isItem", isItem);
				request.setAttribute("items", items);
				request.setAttribute("action", "manage");
				
				if(page != null && page.equals("dashboard")){
					RequestDispatcher rd = request.getRequestDispatcher("admin_dashboard.jsp");
					rd.forward(request, response);
				}else{
					RequestDispatcher rd = request.getRequestDispatcher("items.jsp");
					rd.forward(request, response);
				}	
			}else if(action.equals("approve")){
				String status = request.getParameter("status");
				String page = request.getParameter("page");
				
				Database dbConn = (Database)getServletContext().getAttribute("dbConn");
				ItemDAO itemDAO = new ItemDAOImpl();
				
				Boolean isItem = false;
				int check = 0;
				check = Functions.check(dbConn, "ID", "ITEMS", id);
				if(check > 0){
					isItem = true;
				}
				
				Integer approveResult = null;
				
				if(isItem){
					approveResult = itemDAO.approveItem(dbConn, id);
				}
				ArrayList<Item> items = null;
				if(status != null && status.equals("pending")){
					items = itemDAO.getAllItems(dbConn,status);
				}else{
					items = itemDAO.getAllItems(dbConn);
				}
				
				request.setAttribute("approveResult", approveResult);
				request.setAttribute("isItem", isItem);
				request.setAttribute("items", items);
				request.setAttribute("action", "manage");
				
				if(page != null && page.equals("dashboard")){
					RequestDispatcher rd = request.getRequestDispatcher("admin_dashboard.jsp");
					rd.forward(request, response);
				}else{
					RequestDispatcher rd = request.getRequestDispatcher("items.jsp");
					rd.forward(request, response);
				}
			}	
		}else{
			String status = request.getParameter("status");
			
			Database dbConn = (Database)getServletContext().getAttribute("dbConn");
			ItemDAO itemDAO = new ItemDAOImpl();
			ArrayList<Item> items = null;
			
			if(status != null && status.equals("pending")){
				items = itemDAO.getAllItems(dbConn, status);
				request.setAttribute("status", status);
			}else{
				items = itemDAO.getAllItems(dbConn);
			}	
			
			request.setAttribute("items", items);
			request.setAttribute("action", "manage");
			
			RequestDispatcher rd = request.getRequestDispatcher("items.jsp");
			rd.forward(request, response);		
		}
	}
}