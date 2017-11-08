<!--
	Page Name 		: profile.jsp
	Dev Name  		: Mohammed Darwish
	Creation Date 	: 11/7/2017
-->
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.ArrayList, com.dkaken.model.Item" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.dkaken.resources.messages"/>

<c:set var="user" value="${requestScope.user}"/>
<c:set var="errors" value="${requestScope.errors}"/>
<c:set var="editResult" value="${requestScope.editResult}"/>
<c:set var="isUserExist" value="${requestScope.isUserExist}"/>
<c:set var="action" value="${requestScope.action}"/>
<c:set var="userItems" value="${requestScope.userItems}"/>
<c:set var="userComments" value="${requestScope.userComments}"/>

<jsp:include page="init.jsp">
	<jsp:param value="My Profile" name="page_title"/>
</jsp:include>
			
<c:choose>
	<c:when test="${sessionScope.user_name != null}">
		<div class="profile">
			<c:choose>
				<c:when test="${action == 'edit' }">
					<c:choose>
						<c:when test="${user != null}">
							<div class="container">
								<h1 class="text-center"><fmt:message key="profile.my_profile"/></h1>
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
									<c:if test="${editResult == 1}">
										<div class="success-msg form-group wow slideInDown">
											<div>
												<i class="fa fa-check fa-3x"></i>
												<span><fmt:message key="notify.editResultSuccess"/></span>
											</div>
										</div>
									</c:if>
									<c:if test="${editResult == 0}">
										<div class="fail-msg form-group wow slideInDown">
											<div>
												<i class="fa fa-close fa-3x"></i>
												<span><fmt:message key="notify.editResultFail"/></span>
											</div>
										</div>
									</c:if>
									<c:if test="${isUserExist == true}">
										<div class="fail-msg form-group wow slideInDown">
											<div>
												<i class="fa fa-close fa-3x"></i>
												<span><fmt:message key="error.isUserExist"/></span>
											</div>
										</div>
									</c:if>
								</div>				
								<div class="options text-center">
									<i class="fa fa-refresh fa-lg relode"></i>
								</div>													
								<form class="custom-form users-form" action="profile.do" method="POST" enctype="multipart/form-data">
									<input type="hidden" name="action" value="edit" >
									<input id="id" type="hidden" name="id" value="${user.id}">
									<div class="row">	
										<c:choose>
											<c:when test="${user.profilePic != ''}">
												<div class="col-sm-12"> 
													<div class="user-profile-pic" id="test"> 
														<img class="img-responsive img-thumbnail img-circle" src="${initParam.uploads_dir}${user.profilePic}" alt="${user.username}">
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<div class="col-sm-12"> 
													<div class="user-profile-pic" id="test"> 
														<img class="img-responsive img-thumbnail img-circle" src="img.png" alt="${user.username}">
													</div>
												</div>
											</c:otherwise>
										</c:choose>
										<div class="col-sm-6"> 
											<div class="form-group form-group-lg"> 
												<input
													id="profile-pic"
													class="form-control profile-pic" 
													value="${user.profilePic}" 
													type="file" 
													name="profilePic" 
													style="display: none;"> 
											</div>  
										</div>
									</div>
									<div class="row">
										<div class="col-sm-6">
											<div class="form-group form-group-lg">
												<input 
													class="form-control users_username required" 
													type="text" 
													name="username"
													value="${user.username}" 
													autocomplete="off"
													placeholder="<fmt:message key="profile_eidt.username_placeholder"/>" disabled="disabled">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group form-group-lg">
												<input
													id="password" 
													class="form-control password users_password" 
													type="password" 
													name="newPassword"  
													placeholder="<fmt:message key="profile_edit.password_placeholder"/>" 
													autocomplete="new-password">
												<span id="result"></span>	
												<span class="show-password"><i class="fa fa-eye fa-2x"></i></span>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-6">
											<div class="form-group form-group-lg">
												<input 
													id="email"
													class="form-control users_email required" 
													type="email" 
													name="email"
													value="${user.email}" 
													autocomplete="off"
													placeholder="<fmt:message key="profile_edit.emai_placeholderl"/>">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group form-group-lg">
												<input
													id="fullName" 
													class="form-control users_fullName required" 
													type="text" 
													name="fullName"
													value="${user.fullName}" 
													autocomplete="off"
													placeholder="<fmt:message key="profile_edit.fullName_placeholder"/>">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-6">
											<div class="form-group">
												<input class="btn btn-primary btn-lg" type="submit" value="<fmt:message key="profile_edit.edit_btn"/>">
											</div>	
										</div>
									</div>
								</form>
							</div>
						</c:when>
						<c:otherwise>
							<div class="container">
								<div class="fail-msg form-group ">
									<div>
										<i class="fa fa-close fa-3x"></i>
										<span><fmt:message key="error.noUser"/></span>
									</div>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<div class="information block">
						<div class="container">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<fmt:message key="profile.information" />
								</div>
								<div class="panel-body">
									<ul class="list-unstyled">
										<li>
											<i class="fa fa-unlock fa-fw"></i>
											<span><fmt:message key="profile.username"/></span><a class="hidden-xs">:</a> ${user.username}
										</li>
										<li>
											<i class="fa fa-envelope-o fa-fw"></i>
											<span><fmt:message key="profile.email"/></span><a class="hidden-xs">:</a> ${user.email}
										</li>
										<li>
											<i class="fa fa-user fa-fw"></i>
											<span><fmt:message key="profile.full_name"/></span><a class="hidden-xs">:</a> ${user.fullName}
										</li>
										<li>
											<i class="fa fa-calendar fa-fw"></i>
											<span><fmt:message key="profile.registered_date"/></span><a class="hidden-xs">:</a> ${user.creationDate}
										</li>
										<li>
											<i class="fa fa-tags fa-fw"></i>
											<span><fmt:message key="profile.favourite_category"/></span><a class="hidden-xs">:</a>
										</li>
									</ul>
									<a href="profile.do?action=edit" class="btn btn-primary edit-profile"><fmt:message key="profile.edit_profile"/></a>
								</div>
							</div>
						</div>
					</div>
					<div class="container">
						<div id="my-items" class="ads block"> 
							<div class="title"><fmt:message key="profile.advertisments"/></div>
							<c:choose>
								<c:when test="${userItems != null && userItems.size() != 0}">
									<div id="easyPaginate">
										<c:forEach var="item" items="${userItems}">
											<div class="row">
												<div class="col-sm-12">
									    			<div class="item-box">
														<span class="item-price">${item.price} JD</span>
														<c:if test="${item.isApproved == 0}">
															<span class="item-not-approved">
																<i class="fa fa-clock-o"></i>
															</span>  
															<span class="waitting">Waitting</span> 
														</c:if>
														<div class="item-image">
															<c:choose>
																<c:when test="${item.image != ''}">
																	<img class="image-responsive" src="uploads/item_pics/${item.image}" alt="">			
																</c:when>
																<c:otherwise>
																	<img class="image-responsive" src="img.png" alt="">
																</c:otherwise>
															</c:choose>
														</div>
														<div class="item-info">
															<h3><a href="items.do?id=${item.id}">${item.name}</a></h3>
															<p>${item.description}</p>
															<p class="date">${item.creationDate}</p>
														</div>
													</div>
													<div class="clear-fix"></div>
									    		</div>  
											</div>
										</c:forEach>
									</div>
								</c:when>
								<c:otherwise>
									<div class="no-data-found">
										No items, create <a href="new_item.do">New Item</a>. 
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div id="my-comments" class="comments block">
						<div class="container">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<fmt:message key="profile.comments"/>
								</div>
								<div class="panel-body">
									<c:choose>
										<c:when test="${userComments != null && userComments.size() != 0}">
											<div id="commentPaginate">
												<c:forEach var="comment" items="${userComments}">
													
													<div class="comm">
														<p>${comment.comment}<span>${comment.creationDate}</span></p>
														<a href="#">${comment.item.name}</a>
													</div>
												</c:forEach>
											</div>
										</c:when>
										<c:otherwise>
											<div class="no-data-found">
												No comments to show.
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</c:when>
	<c:otherwise>
		<c:redirect url="login.jsp"></c:redirect>
	</c:otherwise>
</c:choose>
<jsp:include page="includes/templates/footer.jsp"/>