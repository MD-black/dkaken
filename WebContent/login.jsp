<!--
	Page Name 		: login.jsp
	Dev Name  		: Mohammed Darwish
	Creation Date 	: 11/7/2017
-->
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.dkaken.resources.messages" />

<c:if test="${sessionScope.user_name != null}">
	<c:redirect url="/index.jsp"></c:redirect>
</c:if>

<c:set var="errors" value="${requestScope.errors}" />
<c:set var="isUserExist" value="${requestScope.isUserExist}" />
<c:set var="signupUserExist" value="${requestScope.signupUserExist}" />
<c:set var="registrationResult" value="${requestScope.registrationResult}" />


<jsp:include page="init.jsp">
	<jsp:param value="Login|Signup" name="page_title"/>
</jsp:include>
	
<div class="login-page">
	<div class="container">
		<div class="errors">
			<c:if test="${errors != null}">
				<c:forEach var="error" items="${errors}">
					<div class="server-errors wow slideInDown">
						<div class="alert alert-danger alert-dismissible show" role="alert">
						  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
						    <span aria-hidden="true">&times;</span>
						  </button>
						  <strong>!</strong> ${error}
						</div>
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${isUserExist == false}">
				<div class="fail-msg form-group wow slideInDown">
					<div>
						<i class="fa fa-close fa-3x"></i>
						<span><fmt:message key="error.isUserExist"/></span>
					</div>
				</div>
			</c:if>
			<c:if test="${registrationResult == 1}">
				<div class="success-msg form-group wow slideInDown">
					<div>
						<i class="fa fa-check fa-3x"></i>
						<span><fmt:message key="notify.registrationResultSuccess"/></span>
					</div>
				</div>
			</c:if>
			<c:if test="${registrationResult == 0}">
				<div class="fail-msg form-group wow slideInDown">
					<div>
						<i class="fa fa-close fa-3x"></i>
						<span><fmt:message key="notify.registrationResultFail"/></span>
					</div>
				</div>
			</c:if>
			<c:if test="${signupUserExist == true}">
				<div class="fail-msg form-group wow slideInDown">
					<div>
						<i class="fa fa-close fa-3x"></i>
						<span><fmt:message key="error.signupUserExist"/></span>
					</div>
				</div>
			</c:if>
		</div>
		<div class="welcome-pic visible-lg wow slideInLeft">
			<img src="layouts/images/login.png" alt="welcome">
		</div>
		<div class="forms wow slideInRight">
			<h1>
				<span class="active" data-value="login"><fmt:message key="login.login"/></span> | <span data-value="signup"><fmt:message key="login.signup"/></span>
			</h1>
			<form class="login login-form" action="user_login.do" method="post">
				<input type="hidden" name="action" value="login" >
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group form-group-lg">
							<input 
								class="form-control login_username required" 
								type="text" 
								name="username"
								autocomplete="off"
								placeholder="<fmt:message key="login.username_placeholder" />">	
								<!-- Enter Username here. -->
						</div>		
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12"> 
						<div class="form-group form-group-lg">
							<input
								class="form-control password login_password required" 
								type="password" 
								name="password" 
								placeholder="<fmt:message key="login.password_placeholder"/>" 
								autocomplete="new-password"
								required="true" >
								<!-- Enter Password here. -->
							<span class="show-password"><i class="fa fa-eye fa-2x"></i></span>	
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group">
							<input class="btn btn-primary btn-lg btn-login" type="submit" value="<fmt:message key="login.login_btn"/>">
						</div>
					</div>
				</div>
			</form>
			<form class="signup signup-form" action="user_login.do" method="post">
				<input type="hidden" name="action" value="signup" >
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group form-group-lg">
							<input 
								class="form-control signup_username required" 
								type="text" 
								name="username"
								autocomplete="off"
								placeholder="<fmt:message key="login.username_placeholder" />">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group form-group-lg">
							<input
								id="password" 
								class="form-control password signup_password required" 
								type="password" 
								name="password" 
								placeholder="<fmt:message key="login.password_placeholder"/>" 
								autocomplete="new-password"
								required="true" >
							<span id="result"></span>
							<span class="show-password"><i class="fa fa-eye fa-2x"></i></span>	
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group form-group-lg">
							<input 
								class="form-control signup_email required" 
								type="email" 
								name="email"
								autocomplete="off"
								placeholder="<fmt:message key="login.email_placeholder"/>">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group">
							<input class="btn btn-primary btn-lg btn-signup" type="submit" value="<fmt:message key="login.signup_btn"/>">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<jsp:include page="includes/templates/footer.jsp"/>