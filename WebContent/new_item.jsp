<!--
	Page Name 		: new_item.jsp
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
	<jsp:param value="New Item" name="page_title"/>
</jsp:include>
	
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="currentDate" value="${now}" pattern="yyyy/MM/dd"/>
	
<c:set var="errors" value="${requestScope.errors}" scope="request"/>
<c:set var="addResult" value="${requestScope.addResult}" scope="request"/>
<c:set var="userProfilePic" value="${requestScope.userProfilePic}" scope="request"/>
	
<c:choose>
	<c:when test="${sessionScope.user_name != null}">
		<c:set var="categories" value="${requestScope.categories}" />
		<div class="new-item">
			<div class="errors"> 
				<c:if test="${addResult == 1}">
					<div class="success-msg form-group wow slideInDown"> 
						<div>
							<i class="fa fa-check fa-3x"></i>
							<span><fmt:message key="notify.addResultSuccess"/></span>
						</div>
					</div>
				</c:if>
				<c:if test="${addResult == 0}">
					<div class="fail-msg form-group wow slideInDown"> 
						<div>
							<i class="fa fa-close fa-3x"></i>
							<span><fmt:message key="notify.addResultFail"/></span>
						</div>
					</div>
				</c:if>
				<c:if test="${addStatus == false}">
					<div class="fail-msg form-group wow slideInDown"> 
						<div>
							<i class="fa fa-close fa-3x"></i>
							<span><fmt:message key="notify.addStatusFalse"/></span>
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
			<div class="new-item block wow zoomIn">
				<div class="container">
					<h1 class="text-center"><fmt:message key="new_item.newItem"/></h1>
					<div class="row">
						<div class="col-sm-12"> 
				  			<div class="item-box live-preview">
								<span class="item-price"><span class="live-price">0</span> JD</span>
								<span class="upload-icon"><i class="fa fa-upload fa-2x"></i></span> 
								<div class="item-image"> 
									<img class="image-responsive" src="img.png" alt="">
								</div>
								<div class="item-info">
									<p><a class="live-name"><fmt:message key="new_item.item_name"/></a></p>
									<p class="live-desc"><fmt:message key="new_item.description"/></p>
									<p class="date">${currentDate}</p>
									<div class="cat-options">
										<span>
											<i class="fa fa-heart-o fa-fw"></i>
											<fmt:message key="new_item.add_to_favourite"/>
										</span>
										<span>
											<i class="fa fa-commenting-o fa-fw"></i>
											<fmt:message key="new_item.call"/>
										</span>
										<span>
											<i class="fa fa-phone fa-fw"></i>
											<fmt:message key="new_item.chat"/>
										</span>
									</div>
								</div>
								<div class="item-owner">
									<img class="image-responsive" src="${initParam.uploads_dir}${userProfilePic}" alt="">
								</div>
							</div>
				  		</div>  
					</div>					
					<form class="custom-form new-item-form" action="new_item.do" method="POST" enctype="multipart/form-data">
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group form-group-lg">
									<input 
										class="form-control live new-item-name required" 
										type="text" 
										name="name"
										placeholder="<fmt:message key="new_item.itemName_placeholder"/>"
										data-class=".live-name">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group form-group-lg">
									<input 
										class="form-control live new-item-price required" 
										type="text" 
										name="price"
										placeholder="<fmt:message key="new_item.itemPrice_placeholder"/>"
										data-class=".live-price">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group form-group-lg">
									<select name="status" class="new-item-status">
										<option value="0"><fmt:message key="new_item.chooseStatus"/></option>
										<option value="1"><fmt:message key="new_item.chooseStatus.new"/></option>
										<option value="2"><fmt:message key="new_item.chooseStatus.likeNew"/></option>
										<option value="3"><fmt:message key="new_item.chooseStatus.old"/></option>
										<option value="4"><fmt:message key="new_item.chooseStatus.veryOld"/></option>
									</select>
									<input type="hidden" id="newItemStatusValue" value="">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group form-group-lg">
									<select name="category" class="new-item-category">
										<option value="0"><fmt:message key="new_item.chooseCategory"/></option>
										<c:forEach var="category" items="${categories}">
											<option value="${category.id}">${language eq 'en'? category.name : category.nameAr}</option>
											<c:forEach var="childObject" items="${func:getChildCategories(applicationScope['dbConn'],category.id)}">
												<option value="${childObject.id}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${language eq 'en' ? childObject.name : childObject.nameAr}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
											</c:forEach>
										</c:forEach>
									</select>
									<input name="categoryId" type="hidden" id="newItemCategoryValue" value="">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group form-group-lg">
									<input 
										class="form-control new-item-country required" 
										type="text" 
										name="madeIn"
										placeholder="<fmt:message key="new_item.itemCountry_placeholder"/>">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group form-group-lg">
									<input
										data-role="tagsinput" 
										class="form-control" 
										type="text" 
										name="tags" 
										placeholder="<fmt:message key="new_item.itemTags_placeholder"/>">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group form-group-lg"><textarea 
										class="form-control live new-item-description required"
										name="description"
										placeholder="<fmt:message key="new_item.itemDescription"/>" data-class=".live-desc"></textarea>	
								</div>	
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group form-group-lg">
									<input
										id="item-pic" 
										class="form-control item-pic" 
										type="file" 
										name="image" 
										style="display: none;">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<input class="btn btn-primary btn-lg" type="submit" value="<fmt:message key="new_item.add_btn"/>" disabled="true">
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<c:redirect url="login.jsp"></c:redirect>
	</c:otherwise>
</c:choose>
<jsp:include page="includes/templates/footer.jsp"/>