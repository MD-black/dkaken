<!--
	Page Name 		: newItemPic.jsp
	Dev Name  		: Mohammed Darwish
	Creation Date 	: 11/7/2017
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<c:set var="itemImageName" value="${requestScope.itemImageName}" />

<img src="uploads/item_pics/${itemImageName}" alt="">