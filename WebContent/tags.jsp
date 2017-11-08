<!--
	Page Name 		: tags.jsp
	Dev Name  		: Mohammed Darwish
	Creation Date 	: 11/7/2017
-->
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.dkaken.resources.messages" />

<jsp:include page="init.jsp">
	<jsp:param value="Categories" name="page_title"/>
</jsp:include>
	
<c:set var="name" value="${requestScope.name}" />
<c:set var="categoryItems" value="${requestScope.categoryItems}"/>
	
	
<div class="container">
	<h1 class="text-center">${name}</h1>
	<div class="categories">
		<div id="categoryPaginate">
			<c:forEach var="item" items="${categoryItems}">
				<div class="row">
					<div class="col-sm-12"> 
			  			<div class="item-box">
							<span class="item-price">${item.price} JD</span> 
							<div class="item-image">
								<c:choose>
									<c:when test="${item.image != ''}">
										<img class="image-responsive" src="uploads/item_pics/${item.image}" alt="${item.name}">
									</c:when>
									<c:otherwise>
										<img class="image-responsive" src="img.png" alt="${item.name}">
									</c:otherwise>
								</c:choose>
							</div> 
							<div class="item-info">
								<p><a href="items.do?id=${item.id}">${item.name}</a></p>
								<p>${item.description}</p>
								<p class="date">${item.creationDate}</p>
								<div class="cat-options">
									<span>
										<i class="fa fa-heart-o fa-fw"></i>
										<fmt:message key="tags.add_to_favourite"/>
									</span>
									<span>
										<i class="fa fa-commenting-o fa-fw"></i>
										<fmt:message key="tags.call"/>
									</span>
									<span>
										<i class="fa fa-phone fa-fw"></i>
										<fmt:message key="tags.chat"/>
									</span>
								</div>
							</div>
							<div class="item-owner">
								<c:choose>
									<c:when test="${item.user.profilePic != ''}">
										<img class="image-responsive" src="uploads/profile_pics/${item.user.profilePic}" alt="${item.name}">	
									</c:when>
									<c:otherwise>
										<img class="image-responsive" src="img.png" alt="${item.name}">
									</c:otherwise>
								</c:choose> 
							</div>
						</div>
						<div class="clear-fix"></div>
			  		</div>  
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<jsp:include page="includes/templates/footer.jsp"/> 