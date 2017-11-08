<!--
	Page Name 		: index.jsp
	Dev Name  		: Mohammed Darwish
	Creation Date 	: 11/7/2017
-->
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.dkaken.admin.resources.messages" />

<c:if test="${sessionScope.admin_name != null}">
	<c:redirect url="/admin/admin_dashboard.jsp"></c:redirect>
</c:if>

<jsp:include page="init.jsp">
	<jsp:param value="Admin Login" name="page_title"/>
	<jsp:param value="" name="no_navbar"/>
</jsp:include>
	
<div class="login">
	<div class="overlay">
		<div class="container">
			<div class="lang-options">
	   	        <form>
		            <select id="language" name="language" onchange="submit()">
		                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
		                <option value="ar" ${language == 'ar' ? 'selected' : ''}>عربي</option>
		            </select>
		        </form>
	    	</div> 
			<form class="login-form" action="admin_login.do" method="post">
				<h4 class="text-center"><fmt:message key="index.adminLogin"/></h4>
				<div class="form-group">
					<i class="fa fa-user fa-fw fa-2x"><span>|</span></i>
					<input class="form-control" type="text" name="username" placeholder="<fmt:message key="index.adminName_placeholder"/>" autocomplete="off">
				</div>
				<div class="form-group">
					<i class="fa fa-lock fa-fw fa-2x"><span>|</span></i>
					<input class="form-control" type="password" name="password" placeholder="<fmt:message key="index.adminPassword_placeholder"/>" autocomplete="new-password">
				</div>
				<input class="btn btn-primary" type="submit" value="<fmt:message key="index.login_btn"/>">
			</form>
			<div class="copyright">
				<p>&copy; <fmt:message key="index.copyright"/> &reg; DKAKEN</p>
			</div>
		</div>
	</div>
</div>
<jsp:include page="includes/templates/footer.jsp"/>