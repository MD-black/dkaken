<!--
	Page Name 		: init.jsp
	Dev Name  		: Mohammed Darwish
	Creation Date 	: 11/7/2017
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="${initParam.temp_dir}header.jsp">
	<jsp:param value="${param.page_title}" name="title"/>
</jsp:include>

<c:if test="${not(param.no_navbar eq '')}">
	<jsp:include page="${initParam.temp_dir}navbar.jsp"></jsp:include>
</c:if>