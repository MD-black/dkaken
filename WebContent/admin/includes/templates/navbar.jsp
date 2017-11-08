<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.dkaken.admin.resources.messages" />

 
<c:url var="user_edit" value="/admin/user_servlet.do" scope="request">
	<c:param name="action" value="edit"/>
	<c:param name="id" value="${sessionScope.admin_id}"/>
</c:url>
<c:url var="visit_dkaken" value="../index.jsp" scope="request" />
<c:url var="users_page" value="/admin/user_servlet.do" scope="request" />
<c:url var="comments_page" value="/admin/comment_servlet.do" scope="request" />
<c:url var="categories_page" value="/admin/category_servlet.do" scope="request" />
<c:url var="items_page" value="/admin/item_servlet.do" scope="request" />
<nav class="navbar navbar-inverse">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#app-nav" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="admin_dashboard.jsp">
      	<div class="text-center  dk-logo-brand">
			<i class="fa fa-opera" aria-hidden="true"></i>
			<p>DK</p>
		</div>
      </a>
    </div>


    <div class="collapse navbar-collapse" id="app-nav">
      <ul class="nav navbar-nav">
        <li><a href="${categories_page}"><fmt:message key="navbar.categories"/></a></li>
        <li><a href="${items_page}"><fmt:message key="navbar.items"/></a></li>
        <li><a href="${users_page}"><fmt:message key="navbar.users"/></a></li>
        <li><a href="${comments_page}"><fmt:message key="navbar.comments"/></a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="${visit_dkaken}"><fmt:message key="navbar.visitDkaken"/></a></li>
            <li><a href="${user_edit}"><fmt:message key="navbar.editProfile"/></a></li>
            <li><a href="#"><fmt:message key="navbar.setting"/></a></li>
            <li role="separator" class="divider"></li>
            <li><a href="logout.do"><fmt:message key="navbar.logout"/></a></a></li>
          </ul>
        </li>
      </ul>
      <div class="lang-options">
      <!-- 
  	      <form>
  	        <input type="hidden" name="changeLang" value="yes">
            <select id="language" name="language" onchange="submit()">
                <option data-value="bootstrap.min" value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option data-value="bootstrap-rtl.min" value="ar" ${language == 'ar' ? 'selected' : ''}>عربي</option>
        	</select>
        </form>
         -->
   	  </div>
    </div>
  </div>
</nav>