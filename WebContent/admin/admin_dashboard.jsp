<!--
	Page Name 		: admin_dashboard.jsp
	Dev Name  		: Mohammed Darwish
	Creation Date 	: 11/7/2017
-->
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.dkaken.admin.utils.Database,com.dkaken.admin.utils.Functions, java.util.ArrayList" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.dkaken.admin.resources.messages" />

<c:url var="total_users" value="/admin/user_servlet.do" scope="request"/>
<c:url var="total_items" value="/admin/item_servlet.do" scope="request"/>
<c:url var="total_comments" value="/admin/comment_servlet.do" scope="request"/>
<c:url var="pending_users" value="/admin/user_servlet.do" scope="request">
	<c:param name="status" value="pending"/>
</c:url>
<c:url var="pending_items" value="/admin/item_servlet.do" scope="request">
	<c:param name="status" value="pending"/>
</c:url>
<c:url var="pending_comments" value="/admin/comment_servlet.do" scope="request">
	<c:param name="status" value="pending"/>
</c:url>

<%
	String changeLang = request.getParameter("changeLang");
	
	if(changeLang != null && changeLang.equals("yes")){
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
		return;
	}
	
	Database db = (Database)request.getServletContext().getAttribute("dbConn");
	
	int totalUsers = Functions.countRows(db, "ID", "USERS");
	request.setAttribute("totalUsers", totalUsers);
	
	int pendingUsers = Functions.countRows(db, "ID", "USERS", "WHERE REG_STATUS = 0");
	request.setAttribute("pendingUsers", pendingUsers);
	
	int totoalItems = Functions.countRows(db, "ID", "ITEMS");
	request.setAttribute("totoalItems", totoalItems);
	
	int pendingItems = Functions.countRows(db, "ID", "ITEMS", "WHERE IS_APPROVED = 0");
	request.setAttribute("pendingItems", pendingItems);
	
	int totalComments = Functions.countRows(db, "ID", "COMMENTS");
	request.setAttribute("totalComments", totalComments);
	
	int pendingComments = Functions.countRows(db, "ID", "COMMENTS", "WHERE STATUS = 0");
	request.setAttribute("pendingComments", pendingComments);
	
	int usersLimit = 5;
	request.setAttribute("usersLimit",usersLimit);
	ArrayList<Object> users = Functions.getLatest(db, "USERS", "ID", usersLimit);
	request.setAttribute("users", users);
	
	int itemsLimit = 5;
	request.setAttribute("itemsLimit",itemsLimit);
	ArrayList<Object> items = Functions.getLatest(db, "ITEMS", "ID", itemsLimit);
	request.setAttribute("items", items);
	
	int commentsLimit = 5;
	request.setAttribute("commentsLimit",commentsLimit);
	ArrayList<Object> comments = Functions.getLatest(db, "COMMENTS", "ID", commentsLimit);
	request.setAttribute("comments", comments);
%>

<c:set var="editResult" value="${requestScope.editResult}" scope="request"/>
<c:set var="addResult" value="${requestScope.addResult}" scope="request"/>
<c:set var="deleteResult" value="${requestScope.deleteResult}" scope="request"/>
<c:set var="approveResult" value="${requestScope.approveResult}" scope="request"/>
<c:set var="isUser" value="${requestScope.isUser}" scope="request"/>

<c:url var="approve_user" value="/admin/user_servlet.do" scope="request">
	<c:param name="action" value="approve"/>
	<c:param name="page" value="dashboard"/>
</c:url>
<c:url var="edit_user" value="/admin/user_servlet.do" scope="request">
	<c:param name="action" value="edit"/>
	<c:param name="page" value="dashboard"/>
</c:url>
<c:url var="delete_user" value="/admin/user_servlet.do" scope="request">
	<c:param name="action" value="delete"/>
	<c:param name="page" value="dashboard"/>
</c:url>

<c:url var="approve_item" value="/admin/item_servlet.do" scope="request">
	<c:param name="action" value="approve"/>
	<c:param name="page" value="dashboard"/>
</c:url>
<c:url var="edit_item" value="/admin/item_servlet.do" scope="request">
	<c:param name="action" value="edit"/>
	<c:param name="page" value="dashboard"/>
</c:url>
<c:url var="delete_item" value="/admin/item_servlet.do" scope="request">
	<c:param name="action" value="delete"/>
	<c:param name="page" value="dashboard"/>
</c:url>

<c:url var="approve_comment" value="/admin/comment_servlet.do" scope="request">
	<c:param name="action" value="approve"/>
	<c:param name="page" value="dashboard"/>
</c:url>
<c:url var="edit_comment" value="/admin/comment_servlet.do" scope="request">
	<c:param name="action" value="edit"/>
	<c:param name="page" value="dashboard"/>
</c:url>
<c:url var="delete_comment" value="/admin/comment_servlet.do" scope="request">
	<c:param name="action" value="delete"/>
	<c:param name="page" value="dashboard"/>
</c:url>

<c:choose>
	<c:when test="${sessionScope.admin_name != null}">
		<jsp:include page="init.jsp">
			<jsp:param value="Dashboard" name="page_title"/>
		</jsp:include>
			<div class="stats">
				<div class="container text-center">
					<div class="text-center  dk-logo">
						<i class="fa fa-opera" aria-hidden="true"></i>
						<p>DK</p>
					</div>
					<h1 class="text-center"><fmt:message key="admin_dashboard.admin_dashboard"/></h1>
					<div class="row">
						<div class="col-lg-4 col-sm-6">
								<div class="stat st-total-members">
									<i class="fa fa-users green"></i>
									<div class="info">
										<h4><fmt:message key="admin_dashboard.totalUsers"/></h4>
										<span><a href="${total_users}">${totalUsers}</a></span>
									</div>
								</div>
						</div>
						<div class="col-lg-4 col-sm-6">
							<div class="stat st-pending-members">
								<i class="fa fa-user-plus red"></i>
								<div class="info">
									<h4><fmt:message key="admin_dashboard.pendingUsers"/></h4>
									<span><a href="${pending_users}">${pendingUsers}</a></span>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-sm-6">
							<div class="stat st-total-items">
								<i class="fa fa-tags green"></i>
								<div class="info">
									<h4><fmt:message key="admin_dashboard.totalItems"/></h4>
									<span><a href="${total_items}">${totoalItems}</a></span>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-sm-6">
							<div class="stat st-pending-members">
								<i class="fa fa-tag red"></i>
								<div class="info">
									<h4><fmt:message key="admin_dashboard.pendingItems"/></h4>
									<span><a href="${pending_items}">${pendingItems}</a></span>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-sm-6">
							<div class="stat st-total-comments">
								<i class="fa fa-comments-o green"></i>
								<div class="info">
									<h4><fmt:message key="admin_dashboard.totalComments"/></h4>
									<span><a href="${total_comments}">${totalComments}</a></span>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-sm-6">
							<div class="stat st-pending-members">
								<i class="fa fa-commenting-o red"></i>
								<div class="info">
									<h4><fmt:message key="admin_dashboard.pendingComments"/></h4>
									<span><a href="${pending_comments}">${pendingComments}</a></span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="latest">
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
						<c:if test="${isUser == false}">
							<div class="fail-msg form-group wow slideInDown">
								<div>
									<i class="fa fa-close fa-3x"></i>
									<span><fmt:message key="error.isUser"/>.</span>
								</div>
							</div>
						</c:if>
					</div>
					<div class="row">
						<div class="comments">
							<div class="col-xs-12">
								<div class="panel panel-default">
									<div class="panel-heading">
										<span class="panel-plus pull-right">
											<i class="fa fa-plus"></i>
										</span>
										<span class="panel-title">
											<i class="fa fa-comments-o"></i> 
											<span class="hidden-xs"><fmt:message key="admin_dashboard.latest"/> ${commentsLimit} <fmt:message key="admin_dashboard.comments"/></span>
										</span>
										<c:if test="${comments != null && comments.size() != 0 }">
											<div class="main-table-actions"> 
												<span class="others">
													<a href="${approve_comment}" class="btn btn-primary btn-approve" title="<fmt:message key="notify.approve"/>">
														<i class="fa fa-check fa-lg"></i>
													</a>
													<a href="${delete_comment}" class="btn btn-primary btn-delete" title="<fmt:message key="notify.delete"/>">
														<i class="fa fa-close fa-lg"></i>
													</a>
													<a href="${edit_comment}" class="btn btn-primary btn-edit" title="<fmt:message key="notify.edit"/>">
														<i class="fa fa-edit fa-lg"></i>
													</a>
												</span>
											</div>
										</c:if>
									</div>
									<c:choose>
										<c:when test="${comments != null && comments.size() != 0 }">
										<div class="panel-body">
											<div class="table-responsive">
												<table class="main-table table dashboard-table">
													<tbody id="main-table">
														<c:forEach var="comment" items="${comments}">
															<tr class="spacer-tr"></tr>
															<tr>
																<td class="row-id" style="display:none;">${comment.id}</td>
																<td class="col-sm-2">${comment.user.username}</td>
																<td class="spacer-td"></td>
																<td class="row-username col-sm-10">
																	<span>
																		<c:if test="${comment.status == 0}">
																			<i class="fa fa-comments not-approved"></i>
																		</c:if>
																	</span>
																	<span>
																		${comment.comment}
																	</span>																
																</td>
																<td class="spacer-td"></td>
															</tr>
															<tr class="spacer-tr"></tr>
														</c:forEach>
													</tbody>				
												</table>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="panel-body">
											<div class="no-data-found">
												<fmt:message key="notify.no_data_found"/>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<p class="others-msg"><fmt:message key="notify.select_row_first"/></p>
						<div class="users">
							<div class="col-xs-12">
								<div class="panel panel-default">
									<div class="panel-heading">
										<span class="panel-plus pull-right">
											<i class="fa fa-plus"></i>
										</span>
										<span class="panel-title">
											<i class="fa fa-users"></i> 
											<span class="hidden-xs"><fmt:message key="admin_dashboard.latest"/> ${usersLimit} <fmt:message key="admin_dashboard.registeredUsers"/></span>
										</span>
										<c:if test="${users != null && users.size() != 0 }">
											<div class="main-table-actions"> 
												<span class="others">
													<a href="${approve_user}" class="btn btn-primary btn-approve" title="<fmt:message key="notify.approve"/>">
														<i class="fa fa-check fa-lg"></i>
													</a>
													<a href="${delete_user}" class="btn btn-primary btn-delete" title="<fmt:message key="notify.delete"/>">
														<i class="fa fa-close fa-lg"></i>
													</a>
													<a href="${edit_user}" class="btn btn-primary btn-edit" title="<fmt:message key="notify.edit"/>">
														<i class="fa fa-edit fa-lg"></i>
													</a>
												</span>
											</div>
										</c:if>
									</div>
									<c:choose>
										<c:when test="${users != null && users.size() != 0 }">
											<div class="panel-body">
												<div class="table-responsive">
													<table class="main-table text-center table table-bordered dashboard-table">
														<tbody id="main-table">
															<c:forEach var="user" items="${users}">
																<tr>
																	<td class="row-id" style="display:none;">${user.id}</td>
																	<td class="row-username">
																		<span>
																			<c:if test="${user.regStatus == 0}">
																				<i class="fa fa-user not-approved"></i>
																			</c:if>
																		</span>
																		<span>
																			${user.username}
																		</span>																
																	</td>
																</tr>
															</c:forEach>
														</tbody>				
													</table>
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<div class="panel-body">
												<div class="no-data-found">
													<fmt:message key="notify.no_data_found"/>
												</div>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="items">
							<div class="col-xs-12">
								<div class="panel panel-default">
									<div class="panel-heading">
										<span class="panel-plus pull-right">
											<i class="fa fa-plus"></i>
										</span>
										<span class="panel-title">
											<i class="fa fa-tags"></i> 
											<span class="hidden-xs"><fmt:message key="admin_dashboard.latest"/> ${itemsLimit} <fmt:message key="admin_dashboard.items"/></span>
										</span>
										<c:if test="${items != null && items.size() != 0 }">
											<div class="main-table-actions"> 
												<span class="others">
													<a href="${approve_item}" class="btn btn-primary btn-approve" title="<fmt:message key="notify.approve"/>">
														<i class="fa fa-check fa-lg"></i>
													</a>
													<a href="${delete_item}" class="btn btn-primary btn-delete" title="<fmt:message key="notify.delete"/>">
														<i class="fa fa-close fa-lg"></i>
													</a>
													<a href="${edit_item}" class="btn btn-primary btn-edit" title="<fmt:message key="notify.edit"/>">
														<i class="fa fa-edit fa-lg"></i>
													</a>
												</span>
											</div>
										</c:if>
									</div>
									<c:choose>
										<c:when test="${items != null && items.size() != 0 }">
											<div class="panel-body">
												<div class="table-responsive">
													<table class="main-table text-center table table-bordered dashboard-table">
														<tbody id="main-table">
															<c:forEach var="item" items="${items}">
																<tr>
																	<td class="row-id" style="display:none;">${item.id}</td>
																	<td class="row-username">
																		<span>
																			<c:if test="${item.isApproved == 0}">
																				<i class="fa fa-tag not-approved"></i>
																			</c:if>
																		</span>
																		<span>
																			${item.name}
																		</span>																
																	</td>
																</tr>
															</c:forEach>
														</tbody>				
													</table>
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<div class="panel-body">
												<div class="no-data-found">
													<fmt:message key="notify.no_data_found"/>
												</div>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		<jsp:include page="${initParam.temp_dir}/footer.jsp"/>
	</c:when>
	<c:otherwise>
		<c:redirect url="/admin/index.jsp"/>
	</c:otherwise>
</c:choose>