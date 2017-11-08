<!--
	Page Name 		: home.jsp
	Dev Name  		: Mohammed Darwish
	Creation Date 	: 11/7/2017
-->
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="func" uri="Functions" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.dkaken.resources.messages" />

<jsp:include page="init.jsp">
	<jsp:param value="Home" name="page_title"/>
</jsp:include>	
	
<c:set var="parentCategories" value="${requestScope.parentCategories}"/>
	
<div class="home-page">
	<div class="container">
		<c:forEach var="category" items="${parentCategories}" varStatus="i">
			<c:choose>
				<c:when test="${language eq 'en'}">
					<div id="myModal" class="modal fade" role="dialog">
					 	<div class="modal-dialog">
					 		<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title"></h4>
								</div>
								<div class="modal-body">
								
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="notify.close"/></button> 
								</div>
					 		</div>
					 	</div>
					 </div>
					<span class="category num-${i.index} wow zoomIn">
						<span class="parent">
							<a class="cat-${i.index + 1}"  href="category_servlet.do?id=${category.id}&name=${func:replace(' ','+',category.name)}"> ${category.name}</a>
							<span data-id="${category.id}" data-name="${category.name}" data-toggle="modal" data-target="#myModal" class="more"><fmt:message key="notify.more"/></span>
						</span>
						<c:if test="${fn:length(func:getChildCategoriesForUsers(applicationScope['dbConn'],category.id, 1000)) > 0}">	
							<c:forEach var="childObject" items="${func:getChildCategoriesForUsers(applicationScope['dbConn'],category.id, 5)}" varStatus="j">
								<a class="child" href="category_servlet.do?id=${childObject.id}&name=${func:replace(' ','+',childObject.name)}">
									${childObject.name}
								</a>
								<c:if test="${ (j.index+1) % 3 == 0 }"> 
									<div class="devider"></div>
								</c:if> 
							</c:forEach>
						</c:if>
					</span>
					<c:if test="${ (i.index+1) % 3 == 0 }">
						<div class="devider"></div>
					</c:if>
				</c:when>
				<c:otherwise>
					<div id="myModal" class="modal fade" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title"></h4>
								</div>
								<div class="modal-body">
								
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="notify.close"/></button>
								</div>
							</div>
						</div>
					</div>
					<span class="category num-${i.index} wow zoomIn">
						<span class="parent">
							<a class="cat-${i.index + 1}" href="category_servlet.do?id=${category.id}&nameAr=${func:replace(' ','+',category.nameAr)}"> ${category.nameAr}</a>
							<span data-id="${category.id}" data-name="${category.nameAr}" data-toggle="modal" data-target="#myModal" class="more"><fmt:message key="notify.more"/></span>
						</span>
						<c:if test="${fn:length(func:getChildCategoriesForUsers(applicationScope['dbConn'],category.id, 1000)) > 0}">	
							<c:forEach var="childObject" items="${func:getChildCategoriesForUsers(applicationScope['dbConn'],category.id, 5)}" varStatus="j">
								<a class="child" href="category_servlet.do?id=${childObject.id}&name=${func:replace(' ','+',childObject.name)}">
									${childObject.nameAr}
								</a>
								<c:if test="${ (j.index+1) % 3 == 0 }"> 
									<div class="devider"></div>
								</c:if> 
							</c:forEach>
						</c:if>
					</span>
					<c:if test="${ (i.index+1) % 3 == 0 }">
						<div class="devider"></div>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
</div>
<jsp:include page="includes/templates/footer.jsp"/>