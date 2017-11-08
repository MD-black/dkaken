/**
* CategoryServlet Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dkaken.admin.dao.CategoryDAO;
import com.dkaken.admin.dao.CategoryDAOImpl;
import com.dkaken.admin.dto.CategoryDTO;
import com.dkaken.admin.model.Category;
import com.dkaken.admin.utils.Database;
import com.dkaken.admin.utils.Functions;

public class CategoryServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		if(action != null){
			if(action.equals("edit")){
				Category category = new Category();
				
				String name = request.getParameter("name");
				String nameAr = request.getParameter("nameAr");
				String description = request.getParameter("description");
				String descriptionAr = request.getParameter("descriptionAr");
				String ordering = request.getParameter("ordering");
				String isVisible = request.getParameter("isVisible");
				String isCommentAllowed = request.getParameter("isCommentAllowed");
				String isAdsAllowed = request.getParameter("isAdsAllowed");
				String parent = request.getParameter("parent");
				
				String strId = request.getParameter("id");
				int id = 0;
				if( Functions.isNumeric(strId) ){
					id = Integer.valueOf(strId);
				}
				
				category.setName(name);
				category.setNameAr(nameAr);
				category.setId(id);
				category.setDescription(description);
				category.setDescriptionAr(descriptionAr);
				category.setOrdering(ordering);
				if(isVisible != null){category.setIsVisible(Integer.valueOf(isVisible));}
				if(isCommentAllowed != null){category.setIsCommentAllowed(Integer.valueOf(isCommentAllowed));}
				if(isAdsAllowed != null){category.setIsAdsAllowed(Integer.valueOf(isAdsAllowed));}
				if(parent != null){category.setParent(Integer.valueOf(parent));}
				
				CategoryDTO categoryDTO = new CategoryDTO();
				ArrayList<String> errors = categoryDTO.validateCategory(category, "edit");
				
				if(errors.size() != 0){
					Database dbConn = (Database)getServletContext().getAttribute("dbConn");
					CategoryDAO categoryDAO = new CategoryDAOImpl();
					ArrayList<Category> parentCategories = categoryDAO.getPrentCategories(dbConn);
					request.setAttribute("parentCategories", parentCategories);
					request.setAttribute("errors", errors);
					request.setAttribute("action", "edit");
					request.setAttribute("category", category);
					RequestDispatcher rd = request.getRequestDispatcher("categories.jsp");
					rd.forward(request, response);
				}else{
					Database dbConn = (Database)getServletContext().getAttribute("dbConn");
					CategoryDAO categoryDAO = new CategoryDAOImpl();
					
					if(category.getOrdering() == null || category.getOrdering().equals("")){
						int maxOrder = Functions.getBiggestOrder(dbConn);
						category.setOrdering(String.valueOf(maxOrder + 1));
					}
					int	editResult = categoryDAO.updateCategory(dbConn, 
												category.getId(), 
												category.getName(), 
												category.getNameAr(),
												category.getDescription(),
												category.getDescriptionAr(),
												category.getParent(),
												Integer.valueOf(category.getOrdering()),
												Integer.valueOf(category.getIsVisible()),
												Integer.valueOf(category.getIsCommentAllowed()),
												Integer.valueOf(category.getIsAdsAllowed()));
					request.setAttribute("editResult", editResult);
					category = categoryDAO.getCategory(dbConn, id);
					ArrayList<Category> parentCategories = categoryDAO.getPrentCategories(dbConn);
					request.setAttribute("action", "edit");
					request.setAttribute("category", category);
					request.setAttribute("parentCategories", parentCategories);
					RequestDispatcher rd = request.getRequestDispatcher("categories.jsp");
					rd.forward(request, response);
				}
			}else if(action.equals("add")){
				Category category = new Category();
				
				String name = request.getParameter("name");
				String nameAr = request.getParameter("nameAr");
				String description = request.getParameter("description");
				String descriptionAr = request.getParameter("descriptionAr");
				String ordering = request.getParameter("ordering");
				String isVisible = request.getParameter("isVisible");
				String isCommentAllowed = request.getParameter("isCommentAllowed");
				String isAdsAllowed = request.getParameter("isAdsAllowed");
				String parent = request.getParameter("parent");
				
				category.setName(name);
				category.setNameAr(nameAr);
				category.setDescription(description);
				category.setDescriptionAr(descriptionAr);
				category.setOrdering(ordering);
				if(isVisible != null){category.setIsVisible(Integer.valueOf(isVisible));}
				if(isCommentAllowed != null){category.setIsCommentAllowed(Integer.valueOf(isCommentAllowed));}
				if(isAdsAllowed != null){category.setIsAdsAllowed(Integer.valueOf(isAdsAllowed));}
				if(parent != null){category.setParent(Integer.valueOf(parent));}
				
				CategoryDTO categoryDTO = new CategoryDTO();
				ArrayList<String> errors = categoryDTO.validateCategory(category, "add");
				
				if(errors.size() != 0){
					Database dbConn = (Database)getServletContext().getAttribute("dbConn");
					CategoryDAO categoryDAO = new CategoryDAOImpl();
					ArrayList<Category> parentCategories = categoryDAO.getPrentCategories(dbConn);
					request.setAttribute("parentCategories", parentCategories);
					request.setAttribute("errors", errors);
					request.setAttribute("action", "add");
					RequestDispatcher rd = request.getRequestDispatcher("categories.jsp");
					rd.forward(request, response);
				}else{
					Database dbConn = (Database)getServletContext().getAttribute("dbConn");
					CategoryDAO categoryDAO = null;
					ArrayList<Category> categories = null;
					Boolean isCategoryExist = null;
					int addResult = 0;
					int check = Functions.check(dbConn, "Name", "CATEGORIES", name);
					if(check > 0){
						isCategoryExist = true;
					}
					if(isCategoryExist == null ){
						if(category.getOrdering() == null || category.getOrdering().equals("")){
							int maxOrder = Functions.getBiggestOrder(dbConn);
							category.setOrdering(String.valueOf(maxOrder + 1));
						}
						categoryDAO = new CategoryDAOImpl();
						addResult = categoryDAO.addCategory(dbConn, 
															category.getName(),
															category.getNameAr(), 
															category.getDescription(),
															category.getDescriptionAr(),
															Integer.valueOf(category.getParent()),
															Integer.valueOf(category.getOrdering()), 
															Integer.valueOf(category.getIsVisible()), 
															Integer.valueOf(category.getIsCommentAllowed()),
															Integer.valueOf(category.getIsAdsAllowed()));
						categories = categoryDAO.getAllCategories(dbConn, "asc");
						request.setAttribute("categories", categories);
						request.setAttribute("addResult", addResult);
					}else{
						categoryDAO = new CategoryDAOImpl();
						categories = categoryDAO.getAllCategories(dbConn, "asc");
						request.setAttribute("categories", categories);
						request.setAttribute("isCategoryExist", isCategoryExist);
					}
					
					request.setAttribute("action", "manage");
					RequestDispatcher rd = request.getRequestDispatcher("categories.jsp");
					rd.forward(request, response);
				}
			}
		}else{
			request.setAttribute("action", "manage");
			
			RequestDispatcher rd = request.getRequestDispatcher("categories.jsp");
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
				CategoryDAO categoryDAO = new CategoryDAOImpl();
				Category category = categoryDAO.getCategory(dbConn, id);
				if(category != null){
					request.setAttribute("category", category);
				}
				
				ArrayList<Category> parentCategories = categoryDAO.getPrentCategories(dbConn);
				request.setAttribute("parentCategories", parentCategories);
				
				request.setAttribute("action", "edit");
				
				
				RequestDispatcher rd = request.getRequestDispatcher("categories.jsp");
				rd.forward(request, response);
			}else if(action.equals("add")){
				
				Database dbConn = (Database)getServletContext().getAttribute("dbConn");
				CategoryDAO categoryDAO = new CategoryDAOImpl();
				ArrayList<Category> parentCategories = categoryDAO.getPrentCategories(dbConn);
				
				request.setAttribute("parentCategories", parentCategories);
				request.setAttribute("action", "add");
				
				RequestDispatcher rd = request.getRequestDispatcher("categories.jsp");
				rd.forward(request, response);
				
			}else if(action.equals("delete")){
				
				Database dbConn = (Database)getServletContext().getAttribute("dbConn");
				CategoryDAO categoryDAO = new CategoryDAOImpl();
				
				Boolean isCategory = false;
				int check = 0;
				check = Functions.check(dbConn, "ID", "CATEGORIES", id);
				if(check > 0){
					isCategory = true;
				}
				
				Integer deleteResult = null;
				
				if(isCategory){
					deleteResult = categoryDAO.deleteCategory(dbConn, id);
				}
				
				ArrayList<Category> categories = categoryDAO.getAllCategories(dbConn, "asc");
				
				request.setAttribute("deleteResult", deleteResult);
				request.setAttribute("isCategory", isCategory);
				request.setAttribute("categories", categories);
				request.setAttribute("action", "manage");
				
				RequestDispatcher rd = request.getRequestDispatcher("categories.jsp");
				rd.forward(request, response);
				
			}
		}else{
			
			String order = "asc";
			List<String> sortOrders = Arrays.asList("asc","desc");
			
			String requestOrder = request.getParameter("order");
			if( requestOrder != null && sortOrders.contains(requestOrder) ){
				order = requestOrder;
			}
			
			Database dbConn = (Database)getServletContext().getAttribute("dbConn");
			CategoryDAO categoryDAO = new CategoryDAOImpl();
			ArrayList<Category> categories = null;
			
			categories = categoryDAO.getAllCategories(dbConn, order);
			
			request.setAttribute("categories", categories);
			request.setAttribute("order", order);
			request.setAttribute("action", "manage");
			
			RequestDispatcher rd = request.getRequestDispatcher("categories.jsp");
			rd.forward(request, response);
		}
	}
}