/**
* LoginServlet Class
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.dkaken.admin.utils.Database;
import com.dkaken.utils.Functions;
import com.dkaken.dao.UserDAO;
import com.dkaken.dao.UserDAOImpl;
import com.dkaken.dto.UserDTO;
import com.dkaken.model.User;

public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		if(action != null){
			if(action.equals("login")){
				User user = new User();
				
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				
				user.setUsername(username);
				user.setPassword(password);
				
				UserDTO userDTO = new UserDTO();
				ArrayList<String> errors = userDTO.validateUser(user, action);
				
				if(errors.size() != 0){
					request.setAttribute("errors", errors);
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				}else{
					Boolean isUserExist = null;
					Database dbConn = (Database)getServletContext().getAttribute("dbConn");
					UserDAO userDAO = new UserDAOImpl();
					user = userDAO.getUser(dbConn, username, password);
					if(user != null){
						HttpSession session = request.getSession();
						session.setAttribute("user_name", user.getUsername());
						session.setAttribute("user_id", user.getId());
						
						response.sendRedirect("index.jsp");
					}else{
						isUserExist = false;
						request.setAttribute("isUserExist", isUserExist);
						RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
						rd.forward(request, response);
					}
				}
			}else if(action.equals("signup")){
				User user = new User();
				
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String email = request.getParameter("email");
				
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
				
				UserDTO userDTO = new UserDTO();
				ArrayList<String> errors = userDTO.validateUser(user, "signup");
				
				if(errors.size() != 0){
					request.setAttribute("errors", errors);
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				}else{
					Database dbConn = (Database)getServletContext().getAttribute("dbConn");
					UserDAO userDAO = null;
					Boolean signupUserExist = null;
					int registrationResult = 0;
					int check = Functions.check(dbConn, "USERNAME", "USERS", username);
					if(check > 0){
						signupUserExist = true;
					}
					if(signupUserExist == null ){
						userDAO = new UserDAOImpl();
						password = Functions.encryptPassword(password);
						registrationResult = userDAO.registerUser(dbConn, username, password, email);
						request.setAttribute("registrationResult", registrationResult);
					}else{
						request.setAttribute("signupUserExist", signupUserExist);
					}
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				}
			}
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