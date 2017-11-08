<!--
	Page Name 		: childModal.jsp
	Dev Name  		: Mohammed Darwish
	Creation Date 	: 11/7/2017
-->
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="func" uri="Functions" %>

<c:set var="selectedCategoryId" value="${requestScope.selectedCategoryId}" />
<c:set var="selectedCategoryName" value="${requestScope.selectedCategoryName}" />

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.dkaken.resources.messages" />

<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">${selectedCategoryName}</h4>
      </div>
      <div class="modal-body">
       <c:if test="${fn:length(func:getChildCategoriesForUsers(applicationScope['dbConn'],selectedCategoryId, 1000)) > 0}">
			<c:forEach var="childObject" items="${func:getChildCategoriesForUsers(applicationScope['dbConn'], selectedCategoryId, 1000)}" varStatus="j">
				<a class="child" href="category_servlet.do?id=${childObject.id}&name=${func:replace(' ','+',childObject.name)}">
					${language eq 'en' ? childObject.name : childObject.nameAr}
				</a>
				<c:if test="${ (j.index+1) % 3 == 0 }"> 
					<div class="devider"></div>
				</c:if> 
			</c:forEach>
			
		</c:if>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>