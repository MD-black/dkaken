<!--
	Page Name 		: profilePic.jsp
	Dev Name  		: Mohammed Darwish
	Creation Date 	: 11/7/2017
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<c:set var="pPic" value="${requestScope.profilePic}" />

<img class="img-responsive img-thumbnail img-circle" src="${initParam.uploads_dir}${pPic}" alt="">  
