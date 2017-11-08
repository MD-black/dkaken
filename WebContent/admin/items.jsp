<!--
	Page Name 		: items.jsp
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
<fmt:setBundle basename="com.dkaken.admin.resources.messages" />

<c:choose>
	<c:when test="${sessionScope.admin_name != null}"> 
		<jsp:include page="init.jsp">
			<jsp:param value="Items" name="page_title"/> 
		</jsp:include>
		
		<c:set var="items" value="${requestScope.items}" />
		<c:set var="item" value="${requestScope.item}" />
		<c:set var="users" value="${requestScope.users}" />
		<c:set var="categories" value="${requestScope.categories}" />
		<c:set var="comments" value="${requestScope.comments}" />
		
		<c:set var="editResult" value="${requestScope.editResult}" scope="request"/>
		<c:set var="addResult" value="${requestScope.addResult}" scope="request"/>
		<c:set var="deleteResult" value="${requestScope.deleteResult}" scope="request"/>
		<c:set var="approveResult" value="${requestScope.approveResult}" scope="request"/>
		<c:set var="isItem" value="${requestScope.isItem}" scope="request"/>
		<c:set var="isComment" value="${requestScope.isComment}" />
		<c:set var="errors" value="${requestScope.errors}" scope="request"/>
		
		<c:url var="add_item" value="/admin/item_servlet.do" scope="request">
			<c:param name="action" value="add"/>
		</c:url>
		<c:url var="delete_item" value="/admin/item_servlet.do" scope="request">
			<c:param name="action" value="delete"/>
		</c:url>
		<c:url var="approve_item" value="/admin/item_servlet.do" scope="request">
			<c:param name="action" value="approve"/>
		</c:url>
		<c:url var="edit_item" value="/admin/item_servlet.do" scope="request">
			<c:param name="action" value="edit"/>
		</c:url>
		
		<c:url var="delete_comment" value="/admin/comment_servlet.do" scope="request">
			<c:param name="action" value="delete"/>
			<c:param name="page" value="items"/>
			<c:param name="itemId" value="${item.id}"/>
		</c:url>
		<c:url var="approve_comment" value="/admin/comment_servlet.do" scope="request">
			<c:param name="action" value="approve"/>
			<c:param name="page" value="items"/>
			<c:param name="itemId" value="${item.id}"/>
		</c:url>
		<c:url var="edit_comment" value="/admin/comment_servlet.do" scope="request">
			<c:param name="action" value="edit"/>
			<c:param name="page" value="items"/>
		</c:url>
		
		<c:choose>
			<c:when test="${action == 'edit' }">
				<c:choose>
					<c:when test="${item != null}">
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
							<h1 class="text-center"><fmt:message key="items.edit.editItem"/></h1>	
							<form class="custom-form items-form" action="item_servlet.do" method="POST" enctype="multipart/form-data">
								<input id="action" type="hidden" name="action" value="edit" >
								<input type="hidden" name="id" value="${item.id}">
								<div class="row">
									<div class="col-xs-12 col-md-4">
										<div class="form-group form-group-lg">
											<input 
												class="form-control item-name required" 
												type="text" 
												name="name"
												placeholder="<fmt:message key="items.edit.name_placeholder"/>"
												value="${item.name}">
										</div>
									</div>
									<div class="col-xs-12 col-md-4">
										<div class="form-group form-group-lg">
											<input 
												class="form-control item-price required" 
												type="text" 
												name="price"
												placeholder="<fmt:message key="items.edit.price_placeholder"/>"
												value="${item.price}">
										</div>
									</div>
									<div class="col-xs-12 col-md-4">
										<div class="form-group form-group-lg">
											<input 
												class="form-control item-country required" 
												type="text" 
												name="madeIn"
												placeholder="<fmt:message key="items.edit.madeIn_placeholder"/>"
												value="${item.madeIn}">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12 col-md-4">
										<div class="form-group form-group-lg">
											<select name="status" class="item-status">
												<option value="1" ${item.status == '1' ? 'selected' : '' }><fmt:message key="items.edit.status.new"/></option>
												<option value="2" ${item.status == '2' ? 'selected' : '' }><fmt:message key="items.edit.status.likeNew"/></option>
												<option value="3" ${item.status == '3' ? 'selected' : '' }><fmt:message key="items.edit.status.old"/></option>
												<option value="4" ${item.status == '4' ? 'selected' : '' }><fmt:message key="items.edit.status.veryOld"/></option>
											</select>
										</div>
									</div>
									<div class="col-xs-12 col-md-4">
										<div class="form-group form-group-lg">
											<select name="user" class="item-user">
												<c:forEach var="user" items="${users}">
													<option value="${user.id}" 
													${ item.user.id == user.id ? 'selected' : '' }
													>${user.username}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="col-xs-12 col-md-4">
										<div class="form-group form-group-lg">
											<select name="category" class="item-category">
												<c:forEach var="category" items="${categories}">
													<option value="${category.id}" ${ item.category.id == category.id ? 'selected' : '' }>
													 ${language eq 'en' ? category.name : category.nameAr}
													</option>
													 
													<c:forEach var="childObject" items="${func:getChildCategories(applicationScope['dbConn'],category.id)}">
														<option value="${childObject.id}" ${ item.category.id == childObject.id ? 'selected' : '' }
														>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														${language eq 'en' ? childObject.name : childObject.nameAr}
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</option>
													</c:forEach>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group form-group-lg">
											<input
												data-role="tagsinput" 
												class="form-control" 
												type="text" 
												name="tags" 
												value="${item.tags}"
												placeholder="<fmt:message key="items.edit.tags_placeholder"/>">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group form-group-lg">
											<textarea 
													class="form-control item-description required"
													name="description"
													placeholder="<fmt:message key="items.edit.discription_placeholder"/>"
													>${item.description}</textarea>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group form-group-lg">
											<input 
												class="form-control" 
												type="file" 
												name="image" 
												required="true">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12 col-md-4">
										<div class="form-group">
											<input class="btn btn-primary btn-lg" type="submit" value="<fmt:message key="items.edit.editItemBtn"/>">
										</div>	
									</div>
								</div>
							</form>
							<!-- Start Comment -->
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
							<h1 class="text-center">${item.name} <fmt:message key="items.edit.comments.comments"/></h1>
							<p class="others-msg"><fmt:message key="notify.select_row_first"/></p>
							<div class="manage-table">
							<div class="main-table-actions">
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
							</div>
							<div class="table-responsive">
								<table id="table_id" class="display main-table text-center table table-bordered responsive no-wrap" width="100%">
									<thead>
										<tr> 
											<td class="row-id col-xs-1"><fmt:message key="items.edit.comments.id"/></td>
											<td class="col-xs-5"><fmt:message key="items.edit.comments.comment"/></td>
											<td class="col-xs-3"><fmt:message key="items.edit.comments.username"/></td>
											<td class="col-xs-3"><fmt:message key="items.edit.comments.addDate"/></td>
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
												<td>${comment.user.username}</td>
												<td>${comment.creationDate}</td>
											</tr>
										</c:forEach>
									</tbody>				
								</table>
							</div>
							</div>
							<!-- End Comment -->
						</div>
					</c:when>
					<c:otherwise>
						<div class="container">
							<div class="fail-msg form-group wow slideInDown">
								<div>
									<i class="fa fa-close fa-3x"></i>
									<span><fmt:message key="error.noItem"/></span>
								</div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${action == 'add' }">
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
					</div>
					<h1 class="text-center"><fmt:message key="items.add.addNewItem"/></h1>	
					<form class="custom-form items-form" action="item_servlet.do" method="POST" enctype="multipart/form-data">
						<input id="action" type="hidden" name="action" value="add" >
						<div class="row">
							<div class="col-xs-12 col-md-4">
								<div class="form-group form-group-lg">
									<input 
										class="form-control item-name required" 
										type="text" 
										name="name"
										placeholder="<fmt:message key="items.add.name_placeholder"/>">
								</div>
							</div>
							<div class="col-xs-12 col-md-4">
								<div class="form-group form-group-lg">
									<input 
										class="form-control item-price required" 
										type="text" 
										name="price"
										placeholder="<fmt:message key="items.add.price_placeholder"/>">
								</div>
							</div>
							<div class="col-xs-12 col-md-4">
								<div class="form-group form-group-lg">
									<input 
										class="form-control item-country required" 
										type="text" 
										name="madeIn"
										placeholder="<fmt:message key="items.add.madeIn_placeholder"/>">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-md-4">
								<div class="form-group form-group-lg">
									<select name="status" class="item-status">
										<option value="0"><fmt:message key="items.add.status"/></option>
										<option value="1"><fmt:message key="items.add.status.new"/></option>
										<option value="2"><fmt:message key="items.add.status.likeNew"/></option>
										<option value="3"><fmt:message key="items.add.status.old"/></option>
										<option value="4"><fmt:message key="items.add.status.veryOld"/></option>
									</select>
									<input type="hidden" id="itemStatusValue" value="">
								</div>
							</div>
							<div class="col-xs-12 col-md-4">
								<div class="form-group form-group-lg">
									<select name="user" class="item-user">
										<option value="0"><fmt:message key="items.add.user"/></option>
										<c:forEach var="user" items="${users}">
											<option value="${user.id}">${user.username}</option>
										</c:forEach>
									</select>
									<input type="hidden" id="itemUserValue" value="">
								</div>
							</div>
							<div class="col-xs-12 col-md-4">
								<div class="form-group form-group-lg">
									<select name="category" class="item-category">
										<option value="0"><fmt:message key="items.add.category"/></option>
										<c:forEach var="category" items="${categories}">
											<option value="${category.id}">
											${language eq 'en' ? category.name : category.nameAr}
											</option>
											
											<c:forEach var="childObject" items="${func:getChildCategories(applicationScope['dbConn'],category.id)}">
												<option value="${childObject.id}">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												${language eq 'en' ? childObject.name : childObject.nameAr}
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</option>
											</c:forEach>	
												
										</c:forEach>
									</select>
									<input type="hidden" id="itemCategoryValue" value="">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<div class="form-group form-group-lg">
									<input
										data-role="tagsinput" 
										class="form-control" 
										type="text" 
										name="tags" 
										placeholder="<fmt:message key="items.add.tags_placeholder"/>">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<div class="form-group form-group-lg">
									<textarea 
											class="form-control item-description required"
											name="description"
											placeholder="<fmt:message key="items.add.discription_placeholder"/>"></textarea>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<div class="form-group form-group-lg">
									<input 
										class="form-control" 
										type="file" 
										name="image" 
										required="true">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-md-4">
								<div class="form-group">
									<input class="btn btn-primary btn-lg" type="submit" value="<fmt:message key="items.add.addItem"/>">
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
						<c:if test="${isItem == false}">
							<div class="fail-msg form-group wow slideInDown">
								<div>
									<i class="fa fa-close fa-3x"></i>
									<span><fmt:message key="error.isItem"/></span>
								</div>
							</div>
						</c:if>
					</div>
					<div class="text-center  dk-logo">
						<i class="fa fa-opera" aria-hidden="true"></i>
						<p>DK</p>
					</div>
					<h1 class="text-center"><fmt:message key="items.manage.items"/></h1>
					<p class="others-msg"><fmt:message key="notify.select_row_first"/></p>
					<div class="manage-table">
						<div class="main-table-actions">
							<span class="add">
								<a href="${add_item}" class="btn btn-primary btn-add">
									<i class="fa fa-plus fa-lg"></i>
									<fmt:message key="items.manage.newItem"/>
								</a>
							</span>
							<c:if test="${items != null && items.size() != 0 }">
								<span class="others">
									<a href="${approve_item}" class="btn btn-primary btn-approve" title="<fmt:message key="notify.approve"/>">
										<i class="fa fa-check fa-lg"></i>
									</a>
									<a href="${delete_item}" class="btn btn-primary btn-delete confirm" title="<fmt:message key="notify.delete"/>">
										<i class="fa fa-close fa-lg"></i>
									</a>
									<a href="${edit_item}" class="btn btn-primary btn-edit" title="<fmt:message key="notify.edit"/>">
										<i class="fa fa-edit fa-lg"></i>
									</a>
								</span>
							</c:if>
						</div>
						<c:choose>
							<c:when test="${items != null && items.size() != 0 }">
								<div class="table-responsive">
									<table id="table_id" class="display main-table text-center table table-bordered responsive no-wrap" width="100%">
										<thead>
											<tr>
												<td class="row-id col-xs-1"><fmt:message key="items.manage.id"/></td>
												<td class="col-xs-1"><fmt:message key="items.manage.image"/></td>
												<td class="col-xs-2"><fmt:message key="items.manage.name"/></td>
												<td class="col-xs-2"><fmt:message key="items.manage.description"/></td>
												<td class="col-xs-1"><fmt:message key="items.manage.price"/></td>
												<td class="col-xs-1"><fmt:message key="items.manage.categoryName"/></td>
												<td class="col-xs-2"><fmt:message key="items.manage.username"/></td>
												<td class="col-xs-2"><fmt:message key="items.manage.addDate"/></td>
											</tr>
										</thead>
										<tbody id="main-table">
											<c:forEach var="item" items="${items}">
												<tr>
													<td class="row-id">${item.id}</td>
													<td>
														<c:choose>
															<c:when test="${item.image != '' }">
																<img src="uploads/item_pics/${item.image}" alt="${item.name}">
															</c:when>
															<c:otherwise>
																<img src="../img.png" alt="${item.name}">
															</c:otherwise>
														</c:choose>
													</td>
													<td class="row-username">
														<c:if test="${item.isApproved == 0}">
															<i class="fa fa-user not-approved"></i>
														</c:if>
														${item.name}	
													</td>
													<td>${item.description}</td>
													<td>${item.price}</td>
													<td>${item.category.name}</td>
													<td>${item.user.username}</td>
													<td>${item.creationDate}</td>
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