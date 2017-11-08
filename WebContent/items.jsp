<!--
	Page Name 		: items.jsp
	Dev Name  		: Mohammed Darwish
	Creation Date 	: 11/7/2017
-->
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.ArrayList, com.dkaken.model.Item" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.dkaken.resources.messages" />

<c:set var="item" value="${requestScope.item}" />
<c:set var="comments" value="${requestScope.comments}"  scope="request"/>
<c:set var="tags" value="${requestScope.tags}" />


<jsp:include page="init.jsp">
	<jsp:param value="Items" name="page_title"/>
</jsp:include>
	
<div class="container show-item">
	<h1 class="text-center">${item.name}</h1>
	<div class="item">
		<div class="row">
			<div class="col-md-3 item-pic">
				<c:choose>
					<c:when test="${item.image != ''}">
						<img class="img-responsive img-thumbnail center-block" src="uploads/item_pics/${item.image}" alt="">
					</c:when>
					<c:otherwise>
						<img class="img-responsive img-thumbnail center-block" src="img.png" alt="">		
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col-md-9 item-info">
				<h2>${item.name}</h2>
				<p class="item-description">
					${item.description}
					<span class="read-more"><fmt:message key="notify.readMore"/></span> 
				</p>
				<ul class="list-unstyled">
					<li>
						<i class="fa fa-calendar fa-fw"></i>
						<span><fmt:message key="items.info.addedIn"/></span>: ${item.creationDate}		
					</li>
					<li>
						<i class="fa fa-money fa-fw"></i>
						<span><fmt:message key="items.info.price"/></span>: ${item.price}
					</li>
					<li>
						<i class="fa fa-home fa-fw"></i>
						<span><fmt:message key="items.info.madeIn"/></span>: ${item.madeIn}
					</li>
					<li>
						<i class="fa fa-tag fa-fw"></i>
						<span><fmt:message key="items.info.category"/></span>: <a href="category_servlet.do?id=${item.category.id}&name=${item.category.name}">${item.category.name}</a>
					</li>
					<li>
						<i class="fa fa-user fa-fw"></i>
						<span><fmt:message key="items.info.addedBy"/></span>: <a href="#">${item.user.username}</a>
					</li>
					<li>
						<i class="fa fa-tags fa-fw"></i>
						<span><fmt:message key="items.info.tags"/></span>:
						<c:forEach var="tag" items="${tags}">
							<a class="tags-anchors" href="tags_servlet.do?name=${tag}">${tag}</a> <span class="line">|</span> 
						</c:forEach> 
					</li>
				</ul>
			</div>
		</div>
	</div>
	<hr class="custom-hr">
	<c:choose>
		<c:when test="${sessionScope.user_name != null}">
			<c:set var="errors" value="${requestScope.errors}" scope="request"/>
			<c:set var="addResult" value="${requestScope.addResult}" scope="request"/>
			<!--Start Comment-->
			<div class="row">
				<div class="col-md-offset-3">
					<div class="add-comment">
						<h3><fmt:message key="items.comment.addYourComment"/></h3>
						<form class="comment-form" action="comment.do"  method="POST">
							<input type="hidden" name="itemId" value="${item.id}" >
							<div class="form-group form-group-lg">
								<textarea 
									class="form-control items-comment required"
									name="comment"
									placeholder="<fmt:message key="items.comment.comment_placeholder"/>"></textarea>	
							</div>
							<input class="btn btn-primary" type="submit" value="<fmt:message key="items.comment.addComment_btn"/>">
						</form>
					</div>
					<div class="errors"> 
						<c:if test="${addResult == 1}">
							<div class="success-msg form-group wow slideInDown">
								<div>
									<i class="fa fa-check fa-3x"></i>
									<span><fmt:message key="items.comment.commentAddResultSuccess"/></span>
								</div>
							</div>
						</c:if>
						<c:if test="${addResult == 0}">
							<div class="fail-msg form-group wow slideInDown">
								<div>
									<i class="fa fa-close fa-3x"></i>
									<span><fmt:message key="items.comment.commentAddResultFailed"/></span>
								</div>
							</div>
						</c:if>
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
					</div>
				</div>
			</div>
			<!--End Comment-->
		</c:when>
		<c:otherwise>
			<p class="unlogin-user text-center"><a href="login.jsp"><fmt:message key="items.comment.login"/></a> <span> <fmt:message key="items.comment.commentNotification"/></span></p>
		</c:otherwise>		
	</c:choose>
	<hr class="custom-hr">
	<div class="comments-boxs">
		<!-- start for each -->
		<c:forEach var="comment" items="${comments}">
			<div class="comment-box col-md-offset-3 wow zoomIn">
				<div class="row">
					<div class="col-md-2">
						<div class="comment-pic">
							<c:choose>
								<c:when test="${comment.user.profilePic != ''}">
									<img class="img-responsive img-thumbnail img-circle center-block" src="uploads/profile_pics/${comment.user.profilePic}" alt="">
								</c:when>
								<c:otherwise>
									<img class="img-responsive img-thumbnail img-circle center-block" src="img.png" alt="">
								</c:otherwise>
							</c:choose>
							<p>${comment.user.username}</p>
						</div>
					</div>
					<div class="col-md-9">
						<div class="comment-info">
							<p class="lead">${comment.comment}</p>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		<!-- end for each -->
	</div>
</div>
<jsp:include page="includes/templates/footer.jsp"/>