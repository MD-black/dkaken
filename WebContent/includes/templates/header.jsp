<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.dkaken.admin.utils.Database"%>
<%@page import="com.dkaken.utils.Functions"%>
<%@page import="com.dkaken.model.Category"%>
<%@page import="com.dkaken.model.User"%>
<%@page import="com.dkaken.dao.UserDAO"%>
<%@page import="com.dkaken.dao.UserDAOImpl"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="func" uri="Functions" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.dkaken.resources.messages" />

<%
	Database db = (Database)request.getServletContext().getAttribute("dbConn");
	boolean isRegistered = Functions.checkUserStatus(db, (String)session.getAttribute("user_name"));
	request.setAttribute("isRegistered", isRegistered);
%>



    
<!DOCTYPE html>
<html lang="${language}">
	<head>
		<meta charset="utf-8">
		<title>${param.title}</title>
		<link rel="stylesheet" href="http://fonts.googleapis.com/earlyaccess/droidarabickufi.css"/>
		
		<c:choose>
			<c:when test="${sessionScope.language eq 'en' }">
				<link rel="stylesheet" href="${initParam.css_dir}bootstrap.min.css">
					
			</c:when>
			<c:otherwise>
				<link rel="stylesheet" href="${initParam.css_dir}bootstrap.min.css">
				<link rel="stylesheet" href="${initParam.css_dir}bootstrap-rtl.min.css">
			</c:otherwise>
		</c:choose>
		
		<link rel="stylesheet" href="${initParam.css_dir}bootstrap-tagsinput.css">
		
		<link rel="stylesheet" href="${initParam.dt_dir}datatables.css"/>
		<link rel="stylesheet" href="${initParam.css_dir}font-awesome.min.css">
		<link rel="stylesheet" href="${initParam.css_dir}jquery-ui.css">
		<link rel="stylesheet" href="${initParam.css_dir}jquery.selectBoxIt.css">
		<link rel="stylesheet" href="${initParam.css_dir}animate.css"> 
		<c:choose>
			<c:when test="${sessionScope.language eq 'en' }">
				<link rel="stylesheet" href="${initParam.css_dir}style.css">	
			</c:when>
			<c:otherwise>
				<link rel="stylesheet" href="${initParam.css_dir}style.css">
				<link rel="stylesheet" href="${initParam.css_dir}style-rtl.css">
			</c:otherwise>
		</c:choose>
	</head>
	<body>
	
	<div class="upper-bar">
		
	    <div class="container">
	    	<div class="upper-content">
	    	<div class="lang-options">
	    	<!-- 
	   	        <form class="lang-form">
	   	        	<input type="hidden" name="changeLang" value="yes"> 
		            <select id="language" name="language" onchange="submit()">
		                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
		                <option value="ar" ${language == 'ar' ? 'selected' : ''}>عربي</option>
		            </select>
		        </form>
		         -->
	    	</div>
    		<c:choose>
	    		<c:when test="${sessionScope.user_name != null}">
	    			
	    			<%
	    				UserDAO userDAO = new UserDAOImpl();
	    				User user = userDAO.getUser(db,(String)session.getAttribute("user_name"));
	    				request.setAttribute("user",user);
	    			%>
	    			
				    <div class="btn-group">
						<span class="dropdown-toggle" data-toggle="dropdown">
							<span class="caret"></span>
						</span>
						<span class="user-actions">
							<i class="fa fa-question-circle fa-lg"></i>
							<i class="fa fa-bell-o fa-lg"></i>
							<i class="fa fa-comments-o fa-lg"></i>
							<i class="fa fa-user-plus fa-lg"></i>
						</span>
						<ul class="dropdown-menu">
							<li><a href="profile.do"><fmt:message key="header.my_profile"/></a></li>
							<li><a href="new_item.do"><fmt:message key="header.new_item"/></a></li>
							<li><a href="profile.do#my-items"><fmt:message key="header.my_items"/></a></li>
							<li><a href="profile.do#my-comments"><fmt:message key="header.my_comments"/></a></li>
							<li><a href="logout.do"><fmt:message key="header.logout"/></a></li>
						</ul>
				    </div>
				    
				    <c:choose>
	    				<c:when test="${user.profilePic != ''}">
	    					<a class="session-user-profile">
	    						<span class="session-username">${sessionScope.user_name}</span>
		    					<img class="img-responsive img-thumbnail img-circle user-img" src="uploads/profile_pics/${user.profilePic}" alt="">
	    					</a>
	    				</c:when>
	    				<c:otherwise>
	    					<a class="session-user-profile">
	    						<span class="session-username">${sessionScope.user_name}</span>
		    					<img class="img-responsive img-thumbnail img-circle user-img" src="img.png" alt="">
	    					</a>
	    				</c:otherwise>
	    			</c:choose>
				    
					<c:if test="${isRegistered == false}">
						<span class="user-not-approved"><fmt:message key="header.not_activated"/></span>
					</c:if>
					
				</c:when>
				<c:otherwise>
					
	    			<a class="login-signup ${language eq 'en'? 'pull-left' : 'pull-right' }" href="login.jsp"><fmt:message key="header.loging_notification" /></a>
	    			
	    		</c:otherwise>
    		</c:choose>
    		</div>
	    </div>
	    
	  </div>
 	  
	<div class="main-bar">
	  <div class="container">
	  	
	  	<span class="logo">
	      <a class="dk-logo-brand" href="index.jsp">
			<i class="fa fa-opera" aria-hidden="true"></i>
			<span>DK</span>
	      </a>
	     </span>
	     
	  	<span class="latest">
	  		<a href="latest_servlet.do"><fmt:message key="header.latest"/></a>
	  	</span>
	      
	    
	  </div>
	</div>	  
		
		  
		
	