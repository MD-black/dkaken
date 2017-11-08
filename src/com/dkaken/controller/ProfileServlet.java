/**
* ProfileServlet Class
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
import com.dkaken.dto.UserDTO;
import com.dkaken.admin.utils.Database;
import com.dkaken.utils.Functions;
import com.dkaken.dao.UserDAO;
import com.dkaken.dao.UserDAOImpl;
import com.dkaken.model.Comment;
import com.dkaken.model.Item;
import com.dkaken.model.User;

@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
		maxFileSize=1024*1024*10,      // 10MB
		maxRequestSize=1024*1024*50)   // 50MB
public class ProfileServlet extends HttpServlet{
	
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
				String username = (String)request.getSession().getAttribute("user_name");
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
				if(partErrors != null){
					errors.addAll(partErrors);
				}
				
				if(errors.size() != 0){
					request.setAttribute("errors", errors);
					request.setAttribute("action", "edit");
					//get the user profile pic
					Database dbConn = (Database)getServletContext().getAttribute("dbConn");
					UserDAO userDAO = new UserDAOImpl();
					String userPP = userDAO.getUserProfilePic(dbConn, id);
					user.setProfilePic(userPP);
					request.setAttribute("user", user);
					RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
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
						
						user = userDAO.getUser(dbConn, id);
						
						String fileName = null;
						if(partErrors != null){
							fileName = Functions.uploadProfilePic(part);
						}else{
							fileName = user.getProfilePic();  
						}
						
						if(newPassword == null || newPassword.equals("")){
							newPassword = user.getPassword();
						}else{
							newPassword = Functions.encryptPassword(newPassword);
						}
						int	editResult = userDAO.updateUser(dbConn, id, username, fullName, email, newPassword,fileName);
						user = userDAO.getUser(dbConn, id);
						request.setAttribute("editResult", editResult);
						request.setAttribute("user", user);
					}else{
						request.setAttribute("user", user);
						request.setAttribute("isUserExist", isUserExist);
					}
					
					request.setAttribute("action", "edit");
					RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
					rd.forward(request, response);	
				}
			}if(action.equals("editPic")){
				User user = new User();
				
				Part part = request.getPart("profilePic");
				String strId = request.getParameter("id");
				int id = 0;
				if( Functions.isNumeric(strId) ){
					id = Integer.valueOf(strId);
				}
				
				user.setId(id);
				
				UserDTO userDTO = new UserDTO();
				ArrayList<String> errors = Functions.validatePart(part);
				
				if(errors.size() != 0){
					request.setAttribute("errors", errors);
					RequestDispatcher rd = request.getRequestDispatcher("profilePic.jsp");
					rd.forward(request, response);
				}else{
					Database dbConn = (Database)getServletContext().getAttribute("dbConn");
					UserDAO userDAO = new UserDAOImpl();
						
					user = userDAO.getUser(dbConn, id);
					String fileName = null;
					if(errors != null){
						fileName = Functions.uploadProfilePic(part);
					}else{
						fileName = user.getProfilePic();
					}
					
					String profilePic = null;
					int	editResult = userDAO.updateUserProfilePic(dbConn, id, fileName);
					if(editResult > 0){
						profilePic = userDAO.getUserProfilePic(dbConn, id);
					}
					user = userDAO.getUser(dbConn, id);
					request.setAttribute("editResult", editResult);
					request.setAttribute("profilePic", profilePic);
					RequestDispatcher rd = request.getRequestDispatcher("profilePic.jsp");
					rd.forward(request, response);
				}
			}
		}else{
			doGet(request,response);
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
		String sessionUser = (String)request.getSession().getAttribute("user_name");
		
		if(action != null){
			if(action.equals("edit")){
				Database dbConn = (Database)getServletContext().getAttribute("dbConn");
				UserDAO userDAO = new UserDAOImpl();
				User user = userDAO.getUser(dbConn, sessionUser);
				if(user != null){
					request.setAttribute("user", user);
				}
				
				request.setAttribute("action", "edit");
				
				
				RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
				rd.forward(request, response);
			}
		}else{
			if(sessionUser != null && !sessionUser.equals("")){
				Database db = (Database)request.getServletContext().getAttribute("dbConn");
				UserDAO userDAO = new UserDAOImpl();
				User user = userDAO.getUser(db, sessionUser);
				ArrayList<Item> userItems = userDAO.getUserItems(db, user.getId());
				ArrayList<Comment> userComments = userDAO.getUserComments(db, user.getId());
				
				request.setAttribute("user", user);
				request.setAttribute("userItems", userItems);
				request.setAttribute("userComments", userComments);
				RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
				rd.forward(request, response);
			}else{
				response.sendRedirect("login.jsp");
			}
		}
	}
}