<!--
	Page Name 		: comments.jsp
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
			<jsp:param value="Comments" name="page_title"/>
		</jsp:include>
		
		<c:set var="action" value="${requestScope.action}" scope="request"/>
		<c:set var="id" value="${sessionScope.admin_id}"/>
		<c:set var="comment" value="${requestScope.comment}" />
		<c:set var="comments" value="${requestScope.comments}" />
		<c:set var="isComment" value="${requestScope.isComment}" />
		<c:set var="editResult" value="${requestScope.editResult}" scope="request"/>
		<c:set var="addResult" value="${requestScope.addResult}" scope="request"/>
		<c:set var="deleteResult" value="${requestScope.deleteResult}" scope="request"/>
		<c:set var="approveResult" value="${requestScope.approveResult}" scope="request"/>
		<c:set var="status" value="${requestScope.status}" scope="request"/>
		<c:set var="errors" value="${requestScope.errors}" scope="request"/>
		
		<c:url var="delete_comment" value="/admin/comment_servlet.do" scope="request">
			<c:param name="action" value="delete"/>
		</c:url>
		<c:url var="approve_comment" value="/admin/comment_servlet.do" scope="request">
			<c:param name="action" value="approve"/>
		</c:url>
		<c:url var="edit_comment" value="/admin/comment_servlet.do" scope="request">
			<c:param name="action" value="edit"/>
		</c:url>
		
		<c:choose>
			<c:when test="${action == 'edit' }">
				<c:choose>
					<c:when test="${comment != null}">
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
							</div>
							<h1 class="text-center"><fmt:message key="comments.edit.editComment"/></h1>	
							<form class="custom-form comments-form" action="comment_servlet.do" method="POST">
								<input type="hidden" name="action" value="edit" >
								<input type="hidden" name="id" value="${comment.id}" >
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group form-group-lg">
											<textarea
												class="form-control comments_comment required"
												name="comment"
												autocomplete="off"
												placeholder="<fmt:message key="comments.edit.comment_placeholder"/>">${comment.comment}</textarea>
										</div>	
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12 col-md-6">
										<div class="form-group"> 
											<input class="btn btn-primary btn-lg" type="submit" value="<fmt:message key="comments.edit.editCommentBtn"/>">
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
									<span><fmt:message key="error.noComment"/></span>
								</div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${action == 'manage' }">
				<div class="container">
					<div class="errors">
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
						<c:if test="${isComment == false}">
							<div class="fail-msg form-group wow slideInDown">
								<div>
									<i class="fa fa-close fa-3x"></i>
									<span><fmt:message key="error.isComment"/></span>
								</div>
							</div>
						</c:if>
					</div>
					<div class="text-center  dk-logo">
						<i class="fa fa-opera" aria-hidden="true"></i>
						<p>DK</p>
					</div>
					<h1 class="text-center"><fmt:message key="comments.manage.comments"/></h1>
					<p class="others-msg"><fmt:message key="notify.select_row_first"/></p>
					<div class="manage-table">
						<div class="main-table-actions">
							<c:if test="${comments != null && comments.size() != 0 }">
								<span class="others others-comments-actions">
									<a href="${approve_comment}" class="btn btn-primary btn-approve" title="<fmt:message key="notify.approve"/>">
										<i class="fa fa-check fa-lg"></i>
									</a>
									<a href="${delete_comment}" class="btn btn-primary btn-delete confirm" title="<fmt:message key="notify.delete"/>">
										<i class="fa fa-close fa-lg"></i>
									</a>
									<a href="${edit_comment}" class="btn btn-primary btn-edit" title="<fmt:message key="notify.edit"/>">
										<i class="fa fa-edit fa-lg"></i>
									</a>
								</span>
							</c:if>
						</div>
						<c:choose>
							<c:when test="${comments != null && comments.size() != 0 }">
								<div class="table-responsive">
									<table id="table_id" class="display main-table text-center table table-bordered responsive no-wrap" width="100%">
										<thead>
											<tr>
												<td class="row-id col-xs-1"><fmt:message key="comments.manage.id"/></td>
												<td class="col-xs-5"><fmt:message key="comments.manage.comment"/></td>
												<td class="col-xs-2"><fmt:message key="comments.manage.itemName"/></td>
												<td class="col-xs-2"><fmt:message key="comments.manage.username"/></td>
												<td class="col-xs-2"><fmt:message key="comments.manage.addDate"/></td>
											</tr>
										</thead>
										<tbody id="main-table">
											<c:forEach var="comment" items="${comments}">
												<tr>
													<td class="row-id">${comment.id}</td>
													<td class="row-username">
														<c:if test="${comment.status == 0}">
															<i class="fa fa-user not-approved"></i>
														</c:if>
														${comment.comment}	
													</td>
													<td>${comment.item.name}</td>
													<td>${comment.user.username}</td>
													<td>${comment.creationDate}</td>
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