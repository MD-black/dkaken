/**
* UserServlet Class
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
import com.dkaken.admin.dao.UserDAO;
import com.dkaken.admin.dao.UserDAOImpl;
import com.dkaken.admin.dto.UserDTO;
import com.dkaken.admin.model.User;
import com.dkaken.admin.utils.Database;
import com.dkaken.admin.utils.Functions;

@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        		maxFileSize=1024*1024*10,      // 10MB
        		maxRequestSize=1024*1024*50)   // 50MB
public class UserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		if(action != null){
			if(action.equals("edit")){
				User user = new User();
				
				//get fields parameters values.
				String username = request.getParameter("username");
				String email = request.getParameter("email");
				String fullName = request.getParameter("fullName");
				Part part = request.getPart("profilePic");
				String newPassword = request.getParameter("newPassword");
				String strId = request.getParameter("id");
				int id = 0;
				if( Functions.isNumeric(strId) ){
					id = Integer.valueOf(strId);
				}
				
				user.setId(id);
				user.setUsername(username);
				user.setEmail(email);
				user.setFullName(fullName);
				user.setPassword(newPassword);
				
				
				UserDTO userDTO = new UserDTO();
				ArrayList<String> errors = userDTO.validateUser(user, "edit");
				ArrayList<String> partErrors = Functions.validatePart(part);
				errors.addAll(partErrors);
				
				if(errors.size() != 0){
					request.setAttribute("errors", errors);
					request.setAttribute("action", "edit");
					request.setAttribute("user", user);
					RequestDispatcher rd = request.getRequestDispatcher("users.jsp");
					rd.forward(request, response);
				}else{
					Database dbConn = (Database)getServletContext().getAttribute("dbConn");
					
					Boolean isUserExist = null;
					int check = Functions.checkUserOnUpdate(dbConn, "USERNAME", "USERS", user.getUsername(), user.getId());
					
					if(check > 0){
						isUserExist = true;
					}
					UserDAO userDAO = new UserDAOImpl();
					if(isUserExist == null ){
						
						String fileName = Functions.uploadProfilePic(part);
						
						user = userDAO.getUser(dbConn, id);
						if(newPassword == null || newPassword.equals("")){
							newPassword = user.getPassword();
						}else{
							newPassword = Functions.encryptPassword(newPassword);
						}
						int	editResult = userDAO.updateUser(dbConn, id, username, fullName, email, newPassword,fileName);
						request.setAttribute("editResult", editResult);
						user = userDAO.getUser(dbConn, id);
						request.setAttribute("user", user);
					}else{
						request.setAttribute("user", user);
						request.setAttribute("isUserExist", isUserExist);
					}
					
					request.setAttribute("action", "edit");
					RequestDispatcher rd = request.getRequestDispatcher("users.jsp");
					rd.forward(request, response);
				}
			}else if(action.equals("add")){
				
				User user = new User();
				
				//get fields parameters values.
				String username = request.getParameter("username");
				String email = request.getParameter("email");
				String fullName = request.getParameter("fullName");
				String password = request.getParameter("password");
				Part part = request.getPart("profilePic");
				
				user.setUsername(username);
				user.setEmail(email);
				user.setFullName(fullName);
				user.setPassword(password);
				
				UserDTO userDTO = new UserDTO();
				ArrayList<String> errors = userDTO.validateUser(user, "add");
				ArrayList<String> partErrors = Functions.validatePart(part);
				errors.addAll(partErrors);
				
				if(errors.size() != 0){
					request.setAttribute("errors", errors);
					request.setAttribute("action", "add");
					RequestDispatcher rd = request.getRequestDispatcher("users.jsp");
					rd.forward(request, response);
				}else{
					Database dbConn = (Database)getServletContext().getAttribute("dbConn");
					UserDAO userDAO = null;
					ArrayList<User> users = null;
					Boolean isUserExist = null;
					int addResult = 0;
					int check = Functions.check(dbConn, "USERNAME", "USERS", username);
					if(check > 0){
						isUserExist = true;
					}
					if(isUserExist == null ){
						
						String fileName = Functions.uploadProfilePic(part);
						
						userDAO = new UserDAOImpl();
						password = Functions.encryptPassword(password);
						addResult = userDAO.addUser(dbConn, username, password, email, fullName,fileName);
						users = userDAO.getAllUsers(dbConn);
						request.setAttribute("users", users);
						request.setAttribute("addResult", addResult);
					}else{
						userDAO = new UserDAOImpl();
						users = userDAO.getAllUsers(dbConn);
						request.setAttribute("users", users);
						request.setAttribute("isUserExist", isUserExist);
					}
					
					request.setAttribute("action", "manage");
					RequestDispatcher rd = request.getRequestDispatcher("users.jsp");
					rd.forward(request, response);
				}
			}
		}else{
			request.setAttribute("action", "manage");
			
			RequestDispatcher rd = request.getRequestDispatcher("users.jsp");
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
				UserDAO userDAO = new UserDAOImpl();
				User user = userDAO.getUser(dbConn, id);
				if(user != null){
					request.setAttribute("user", user);
				}
				
				request.setAttribute("action", "edit");
				
				
				RequestDispatcher rd = request.getRequestDispatcher("users.jsp");
				rd.forward(request, response);
			}else if(action.equals("add")){
				
				
				request.setAttribute("action", "add");
				
				
				RequestDispatcher rd = request.getRequestDispatcher("users.jsp");
				rd.forward(request, response);
				
			}else if(action.equals("delete")){
				
				String status = request.getParameter("status");
				String page = request.getParameter("page");
				
				Database dbConn = (Database)getServletContext().getAttribute("dbConn");
				UserDAO userDAO = new UserDAOImpl();
				
				Boolean isUser = false;
				int check = 0;
				check = Functions.check(dbConn, "ID", "USERS", id);
				if(check > 0){
					isUser = true;
				}
				
				Integer deleteResult = null;
				
				if(isUser){
					deleteResult = userDAO.deleteUser(dbConn, id);
				}
				
				ArrayList<User> users = null;
				if(status != null && status.equals("pending")){
					users = userDAO.getAllUsers(dbConn, status);
				}else{
					users = userDAO.getAllUsers(dbConn);
				}
				
				request.setAttribute("deleteResult", deleteResult);
				request.setAttribute("isUser", isUser);
				request.setAttribute("users", users);
				request.setAttribute("action", "manage");
				
				if(page != null && page.equals("dashboard")){
					RequestDispatcher rd = request.getRequestDispatcher("admin_dashboard.jsp");
					rd.forward(request, response);
				}else{
					RequestDispatcher rd = request.getRequestDispatcher("users.jsp");
					rd.forward(request, response);
				}
			}else if(action.equals("approve")){
			
				String status = request.getParameter("status");
				String page = request.getParameter("page");
				
				Database dbConn = (Database)getServletContext().getAttribute("dbConn");
				UserDAO userDAO = new UserDAOImpl();
				
				Boolean isUser = false;
				int check = 0;
				check = Functions.check(dbConn, "ID", "USERS", id);
				if(check > 0){
					isUser = true;
				}
				
				Integer approveResult = null;
				
				if(isUser){
					approveResult = userDAO.approveUser(dbConn, id);
				}
				ArrayList<User> users = null;
				if(status != null && status.equals("pending")){
					users = userDAO.getAllUsers(dbConn, status);
				}else{
					users = userDAO.getAllUsers(dbConn);
				}
				
				request.setAttribute("approveResult", approveResult);
				request.setAttribute("isUser", isUser);
				request.setAttribute("users", users);
				request.setAttribute("action", "manage");
				
				if(page != null && page.equals("dashboard")){
					RequestDispatcher rd = request.getRequestDispatcher("admin_dashboard.jsp");
					rd.forward(request, response);
				}else{
					RequestDispatcher rd = request.getRequestDispatcher("users.jsp");
					rd.forward(request, response);
				}
			}
		}else{
			String status = request.getParameter("status");
			
			Database dbConn = (Database)getServletContext().getAttribute("dbConn");
			UserDAO userDAO = new UserDAOImpl();
			ArrayList<User> users = null;
			if(status != null && status.equals("pending")){
				users = userDAO.getAllUsers(dbConn, status);
				request.setAttribute("status", status);
			}else{
				users = userDAO.getAllUsers(dbConn);
			}
			
			request.setAttribute("users", users);
			request.setAttribute("action", "manage");
			
			RequestDispatcher rd = request.getRequestDispatcher("users.jsp");
			rd.forward(request, response);
		}
	}
}