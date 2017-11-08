<!--
	Page Name 		: users.jsp
	Dev Name  		: Mohammed Darwish
	Creation Date 	: 11/7/2017
-->
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.dkaken.admin.resources.messages" />

<c:choose>
	<c:when test="${sessionScope.admin_name != null}">
		
		<jsp:include page="init.jsp">
			<jsp:param value="Users" name="page_title"/>
		</jsp:include>
		
		<c:set var="action" value="${requestScope.action}" scope="request"/>
		<c:set var="id" value="${sessionScope.admin_id}"/>
		<c:set var="user" value="${requestScope.user}" />
		<c:set var="users" value="${requestScope.users}" />
		<c:set var="editResult" value="${requestScope.editResult}" scope="request"/>
		<c:set var="addResult" value="${requestScope.addResult}" scope="request"/>
		<c:set var="deleteResult" value="${requestScope.deleteResult}" scope="request"/>
		<c:set var="approveResult" value="${requestScope.approveResult}" scope="request"/>
		<c:set var="isUserExist" value="${requestScope.isUserExist}" scope="request"/>
		<c:set var="isUser" value="${requestScope.isUser}" scope="request"/>
		<c:set var="status" value="${requestScope.status}" scope="request"/>
		<c:set var="errors" value="${requestScope.errors}" scope="request"/>
		
		<c:url var="add_user" value="/admin/user_servlet.do" scope="request">
			<c:param name="action" value="add"/>
		</c:url>
		<c:url var="delete_user" value="/admin/user_servlet.do" scope="request">
			<c:param name="action" value="delete"/>
		</c:url>
		<c:url var="approve_user" value="/admin/user_servlet.do" scope="request">
			<c:param name="action" value="approve"/>
		</c:url>
		<c:url var="edit_user" value="/admin/user_servlet.do" scope="request">
			<c:param name="action" value="edit"/>
		</c:url>
		
		<c:choose>
			<c:when test="${action == 'edit' }">
				<c:choose>
					<c:when test="${user != null}">
						<div class="container">
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
										<span><fmt:message key="error.userAlreadyExist"/></span>
									</div>
								</div>
							</c:if>
							</div>
							<h1 class="text-center"><fmt:message key="users.edit.editUser"/></h1>	
							<form class="custom-form users-form" action="user_servlet.do" method="POST" enctype="multipart/form-data">
								<input type="hidden" name="action" value="edit" >
								<input type="hidden" name="id" value="${user.id}" >
								<div class="row">
									<div class="col-xs-12 col-md-6">
										<div class="form-group form-group-lg">
											<input 
												class="form-control users_username required" 
												type="text" 
												name="username"
												value="${user.username}" 
												autocomplete="off"
												placeholder="<fmt:message key="users.edit.username_placeholder"/>">
										</div>		
									</div>
									<div class="col-xs-12 col-md-6">
										<div class="form-group form-group-lg">
											<input
												id="password" 
												class="form-control password users_password" 
												type="password" 
												name="newPassword"  
												placeholder="<fmt:message key="users.edit.password_placeholder"/>" 
												autocomplete="new-password">
											<span id="result"></span>	
											<span class="show-password"><i class="fa fa-eye fa-2x"></i></span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12 col-md-6">
										<div class="form-group form-group-lg">
											<input 
												class="form-control users_email required" 
												type="email" 
												name="email"
												value="${user.email}" 
												autocomplete="off"
												placeholder="<fmt:message key="users.edit.email_placeholder"/>">
										</div>
									</div>
									<div class="col-xs-12 col-md-6">
										<div class="form-group form-group-lg">
											<input 
												class="form-control users_fullName required" 
												type="text" 
												name="fullName"
												value="${user.fullName}" 
												autocomplete="off"
												placeholder="<fmt:message key="users.edit.fullName_placeholder"/>">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group form-group-lg">
											<input 
												class="form-control" 
												type="file" 
												name="profilePic" 
												required="true">
										</div>	
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12 col-md-6">
										<div class="form-group">
											<input class="btn btn-primary btn-lg" type="submit" value="<fmt:message key="users.edit.editUserBtn"/>">
										</div>	
									</div>
								</div>
							</form>
						</div>
					</c:when>
					<c:otherwise>
						<div class="container">
							<div class="fail-msg form-group wow slideInDown">
								<div>
									<i class="fa fa-close fa-3x"></i>
									<span><fmt:message key="error.noUser"/></span>
								</div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${action == 'add' }">
				<div class="container">
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
					<h1 class="text-center"><fmt:message key="users.add.addNewUser"/></h1>	
					<form class="custom-form users-form" action="user_servlet.do" method="POST" enctype="multipart/form-data">
						<input type="hidden" name="action" value="add" >
						<div class="row">
							<div class="col-xs-12 col-md-6">
								<div class="form-group form-group-lg">
									<input 
										class="form-control users_username required" 
										type="text" 
										name="username"
										autocomplete="off"
										placeholder="<fmt:message key="users.add.username_placeholder"/>">
								</div>
							</div>
							<div class="col-xs-12 col-md-6">
								<div class="form-group form-group-lg">
									<input
										id="password" 
										class="form-control password users_password required" 
										type="password" 
										name="password" 
										placeholder="<fmt:message key="users.add.password_placeholder"/>" 
										autocomplete="new-password"
										required="true" >
									<span id="result"></span>
									<span class="show-password"><i class="fa fa-eye fa-2x"></i></span>	
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-md-6">
								<div class="form-group form-group-lg">
									<input 
										class="form-control users_email required" 
										type="email" 
										name="email"
										autocomplete="off"
										placeholder="<fmt:message key="users.add.email_placeholder"/>">
								</div>	
							</div>
							<div class="col-xs-12 col-md-6">
								<div class="form-group form-group-lg">
									<input 
										class="form-control users_fullName required" 
										type="text" 
										name="fullName" 
										autocomplete="off"
										placeholder="<fmt:message key="users.add.fullName_placeholder"/>">
								</div>	
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<div class="form-group form-group-lg">
									<input 
										class="form-control" 
										type="file" 
										name="profilePic" 
										required="true">
								</div>	
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-md-6">
								<div class="form-group">
									<input class="btn btn-primary btn-lg" type="submit" value="<fmt:message key="users.add.addUserBtn"/>">
								</div>	
							</div>
						</div>
					</form>
				</div>
			</c:when>
			<c:when test="${action == 'manage' }">
				<div class="container">
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
					<c:if test="${deleteResult == 1}">
						<div class="success-msg form-group wow slideInDown">
							<div>
								<i class="fa fa-check fa-3x"></i>
								<span><fmt:message key="notify.deleteResultSuccess"/></span>
							</div>
						</div>
					</c:if>
					<c:if test="${deleteResult == 0}">
						<div class="fail-msg form-group wow slideInDown">
							<div>
								<i class="fa fa-close fa-3x"></i>
								<span><fmt:message key="notify.deleteResultFail"/></span>
							</div>
						</div>
					</c:if>
					<c:if test="${approveResult == 1}">
						<div class="success-msg form-group wow slideInDown">
							<div>
								<i class="fa fa-check fa-3x"></i>
								<span><fmt:message key="notify.approveResultSuccess"/></span>
							</div>
						</div>
					</c:if>
					<c:if test="${approveResult == 0}">
						<div class="fail-msg form-group wow slideInDown"> 
							<div>
								<i class="fa fa-close fa-3x"></i>
								<span><fmt:message key="notify.approveResultFail"/></span>
							</div>
						</div>
					</c:if>
					<c:if test="${isUserExist == true}">
						<div class="fail-msg form-group wow slideInDown">
							<div>
								<i class="fa fa-close fa-3x"></i>
								<span><fmt:message key="error.userAlreadyExist"/></span>
							</div>
						</div>
					</c:if>
					<c:if test="${isUser == false}">
						<div class="fail-msg form-group wow slideInDown"> 
							<div>
								<i class="fa fa-close fa-3x"></i>
								<span><fmt:message key="error.isUser"/></span>
							</div>
						</div>
					</c:if>
					</div>
					<div class="text-center  dk-logo">
						<i class="fa fa-opera" aria-hidden="true"></i>
						<p>DK</p>
					</div>
					<h1 class="text-center"><fmt:message key="users.manage.users"/></h1>
					<p class="others-msg"><fmt:message key="notify.select_row_first"/></p>
					<div class="manage-table">
						<div class="main-table-actions">
							<span class="add">
								<a href="${add_user}" class="btn btn-primary btn-add">
									<i class="fa fa-plus fa-lg"></i>
									<fmt:message key="users.manage.newUser"/>
								</a>
							</span>
							<c:if test="${users != null && users.size() != 0 }">
								<span class="others">
									<a href="${approve_user}" class="btn btn-primary btn-approve" title="<fmt:message key="notify.approve"/>">
										<i class="fa fa-check fa-lg"></i>
									</a>
									<a href="${delete_user}" class="btn btn-primary btn-delete confirm" title="<fmt:message key="notify.delete"/>">
										<i class="fa fa-close fa-lg"></i>
									</a>
									<a href="${edit_user}" class="btn btn-primary btn-edit" title="<fmt:message key="notify.edit"/>">
										<i class="fa fa-edit fa-lg"></i>
									</a>
								</span>
							</c:if>
						</div>
						<c:choose>
							<c:when test="${users != null && users.size() != 0 }">
								<div class="table-responsive">
									<table id="table_id" class="display main-table text-center table table-bordered responsive no-wrap" width="100%">
										<thead>
											<tr>
												<td class="row-id col-xs-1"><fmt:message key="users.manage.id"/></td>
												<td class="col-xs-1"><fmt:message key="users.manage.image"/></td>
												<td class="col-xs-2"><fmt:message key="users.manage.username"/></td>
												<td class="col-xs-3"><fmt:message key="users.manage.email"/></td>
												<td class="col-xs-3"><fmt:message key="users.manage.fullName"/></td>
												<td class="col-xs-2"><fmt:message key="users.manage.registrationDate"/></td>
											</tr>
										</thead>
										<tbody id="main-table">
											<c:forEach var="user" items="${users}">
												<tr>
													<td class="row-id">${user.id}</td>
													<td>
														<c:choose>
															<c:when test="${user.profilePic != '' }">
																<img src="uploads/profile_pics/${user.profilePic}" alt="${user.username}">
															</c:when>
															<c:otherwise>
																<img src="../img.png" alt="${user.username}">
															</c:otherwise>
														</c:choose>
													</td>
													<td class="row-username">
														<c:if test="${user.regStatus == 0}">
															<i class="fa fa-user not-approved"></i>
														</c:if>
														${user.username}	
													</td>
													<td>${user.email }</td>
													<td>${user.fullName}</td>
													<td>${user.creationDate}</td>
												</tr>
											</c:forEach>
										</tbody>				
									</table>
								</div>
							</c:when>
							<c:otherwise>
								<div class="no-data-found">
									<fmt:message key="notify.no_data_found"/>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<p><fmt:message key="notify.no_action"/></p>
			</c:otherwise>
		</c:choose>
		<jsp:include page="${initParam.temp_dir}/footer.jsp"/>
	</c:when>
	<c:otherwise>
		<c:redirect url="/admin/index.jsp"/>
	</c:otherwise>
</c:choose>