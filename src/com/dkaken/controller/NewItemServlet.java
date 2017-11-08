/**
* NewItemServlet Class
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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.dkaken.admin.utils.Database;
import com.dkaken.dao.CategoryDAO;
import com.dkaken.dao.CategoryDAOImpl;
import com.dkaken.dao.ItemDAO;
import com.dkaken.dao.ItemDAOImpl;
import com.dkaken.dao.UserDAO;
import com.dkaken.dao.UserDAOImpl;
import com.dkaken.dto.ItemDTO;
import com.dkaken.model.Category;
import com.dkaken.model.Item;
import com.dkaken.model.User;
import com.dkaken.utils.Functions;

@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
		maxFileSize=1024*1024*10,      // 10MB
		maxRequestSize=1024*1024*50)   // 50MB
public class NewItemServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		if(action != null && action.equals("insertItemPic")){
			
			Part part = request.getPart("image");
			String userId = String.valueOf(request.getSession().getAttribute("user_id"));
			String catId = request.getParameter("catId");
			
			Item item = new Item();
			User user = new User();
			Category category = new Category();
			
			user.setId(Integer.valueOf(userId));
			category.setId(Integer.valueOf(catId));
			item.setUser(user);
			item.setCategory(category);
			
			ItemDTO itemDTO = new ItemDTO();
			ArrayList<String> errors = itemDTO.validateItem(item, "insert");
			ArrayList<String> partErrors = Functions.validatePart(part);
			if(partErrors != null){
				errors.addAll(partErrors);
			}
			
			if(errors.size() != 0){
				request.setAttribute("errors", errors);
				UserDAO userDAO = new UserDAOImpl();
				Database dbConn = (Database)getServletContext().getAttribute("dbConn");
				String userProfilePic = userDAO.getUserProfilePic(dbConn, Integer.valueOf( String.valueOf(request.getSession().getAttribute("user_id"))));
				CategoryDAO categoryDAO = new CategoryDAOImpl();
				ArrayList<Category> categories = categoryDAO.getAllCategories(dbConn);
				request.setAttribute("categories", categories);
				request.setAttribute("userProfilePic", userProfilePic);
				
				RequestDispatcher rd = request.getRequestDispatcher("new_item.jsp");
				rd.forward(request, response);
			}else{
				
				String fileName = Functions.uploadItemPic(part);
				
				Database dbConn = (Database)getServletContext().getAttribute("dbConn");
				ItemDAO itemDAO = null;
				ArrayList<Item> items = null;
				int addResult = 0;
				
				
				itemDAO = new ItemDAOImpl();
				addResult = itemDAO.addItemImage(dbConn, item.getUser().getId(), item.getCategory().getId(), fileName);
				//GETTING THE NEW ITEM ID.
				Item newItem = itemDAO.getLatestUserItem(dbConn, Integer.valueOf(userId), Integer.valueOf(catId)); 
				String itemImageName = newItem.getImage();
				if(addResult > 0){
					request.setAttribute("itemImageName", itemImageName);
				}
				request.setAttribute("addResult", addResult);
				RequestDispatcher rd = request.getRequestDispatcher("newItemPic.jsp");
				rd.forward(request, response);
			}
		}else{
			Item item = new Item();
			Category category = new Category();
			User user = new User();
			
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String price = request.getParameter("price");
			String madeIn = request.getParameter("madeIn");
			String status = request.getParameter("status");
			String userId = String.valueOf(request.getSession().getAttribute("user_id"));
			String catId = request.getParameter("category");
			String tags = request.getParameter("tags");
			
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
			ArrayList<String> errors = itemDTO.validateItem(item, "update");
			
			if(errors.size() != 0){
				request.setAttribute("errors", errors);
				
				Database dbConn = (Database)getServletContext().getAttribute("dbConn");
				CategoryDAO categoryDAO = new CategoryDAOImpl();
				ArrayList<Category> categories = categoryDAO.getAllCategories(dbConn);
				request.setAttribute("categories", categories);
				
				RequestDispatcher rd = request.getRequestDispatcher("new_item.jsp");
				rd.forward(request, response);
			}else{
				Database dbConn = (Database)getServletContext().getAttribute("dbConn");
				ItemDAO itemDAO = null;
				ArrayList<Item> items = null;
				int addResult = 0;
				Boolean addStatus = true;
				
				itemDAO = new ItemDAOImpl();
				Item itemToUpdate = itemDAO.getLatestUserItem(dbConn, Integer.valueOf(userId), Integer.valueOf(catId));
				if(itemToUpdate == null){
					addStatus = false;
					CategoryDAO categoryDAO = new CategoryDAOImpl();
					ArrayList<Category> categories = categoryDAO.getPrentCategories(dbConn);
					request.setAttribute("categories", categories);
				}else if(itemToUpdate != null && itemToUpdate.getName() != null && !itemToUpdate.getName().equals("")){
					addStatus = false;
					CategoryDAO categoryDAO = new CategoryDAOImpl();
					ArrayList<Category> categories = categoryDAO.getPrentCategories(dbConn);
					request.setAttribute("categories", categories);
				}else{ 
					int itemId = itemToUpdate.getId();
					addResult = itemDAO.updateItem(dbConn,
												itemId,
												item.getName(),
												item.getDescription(),
												item.getPrice(),
												item.getMadeIn(),
												item.getStatus(),
												item.getTags());
				}
				UserDAO userDAO = new UserDAOImpl();
				CategoryDAO categoryDAO = new CategoryDAOImpl();
				ArrayList<Category> categories = categoryDAO.getPrentCategories(dbConn);
				request.setAttribute("categories", categories);
				String userProfilePic = userDAO.getUserProfilePic(dbConn, Integer.valueOf( String.valueOf(request.getSession().getAttribute("user_id"))));
				request.setAttribute("userProfilePic", userProfilePic);
				request.setAttribute("addResult", addResult);
				request.setAttribute("addStatus", addStatus);
				RequestDispatcher rd = request.getRequestDispatcher("new_item.jsp");//profile.do#my-items
				rd.forward(request, response);
			}
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
		Database dbConn = (Database)getServletContext().getAttribute("dbConn");
		UserDAO userDAO = new UserDAOImpl();
		String userProfilePic = userDAO.getUserProfilePic(dbConn, Integer.valueOf( String.valueOf(request.getSession().getAttribute("user_id"))));
		CategoryDAO categoryDAO = new CategoryDAOImpl();
		ArrayList<Category> categories = categoryDAO.getPrentCategories(dbConn);
		request.setAttribute("categories", categories);
		request.setAttribute("userProfilePic", userProfilePic);
		RequestDispatcher rd = request.getRequestDispatcher("new_item.jsp");
		rd.forward(request, response);
	}
}