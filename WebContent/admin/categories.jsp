<!--
	Page Name 		: categories.jsp
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
			<jsp:param value="Categories" name="page_title"/>
		</jsp:include>
		
		<c:set var="categories" value="${requestScope.categories}" />
		<c:set var="parentCategories" value="${requestScope.parentCategories}" />
		<c:set var="category" value="${requestScope.category}" />
		<c:set var="addResult" value="${requestScope.addResult}" scope="request"/>
		<c:set var="editResult" value="${requestScope.editResult}" scope="request"/>
		<c:set var="deleteResult" value="${requestScope.deleteResult}" scope="request"/>
		<c:set var="isCategory" value="${requestScope.isCategory}" scope="request"/>
		<c:set var="isCategoryExist" value="${requestScope.isCategoryExist}" scope="request"/>
		<c:set var="order" value="${requestScope.order}" scope="request"/>
		<c:set var="errors" value="${requestScope.errors}" scope="request"/>
		
		<c:url var="asc_order" value="/admin/category_servlet.do" scope="request">
			<c:param name="order" value="asc"/>
		</c:url>
		<c:url var="desc_order" value="/admin/category_servlet.do" scope="request">
			<c:param name="order" value="desc"/>
		</c:url>
		<c:url var="edit_category" value="/admin/category_servlet.do" scope="request">
			<c:param name="action" value="edit"/>
		</c:url>
		<c:url var="delete_category" value="/admin/category_servlet.do" scope="request">
			<c:param name="action" value="delete"/>
		</c:url>
		<c:url var="add_category" value="/admin/category_servlet.do" scope="request">
			<c:param name="action" value="add"/>
		</c:url>
		
		<c:choose>
			<c:when test="${action == 'edit' }">
				<c:choose>
					<c:when test="${category != null}">
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
							<h1 class="text-center"><fmt:message key="categories.editCategory"/></h1>
							<form class="custom-form categories-form" action="category_servlet.do" method="POST">
								<input type="hidden" name="action" value="edit" >
								<input type="hidden" name="id" value="${category.id}" >
								<div class="row">
									<div class="col-xs-12 col-md-6">
										<div class="form-group form-group-lg">
											<input 
												class="form-control category-name required" 
												type="text" 
												name="name"
												placeholder="<fmt:message key="categories.name_placeholder"/>"
												value="${category.name}">
										</div>
									</div>
									<div class="col-xs-12 col-md-6">
										<div class="form-group form-group-lg">
											<input 
												class="form-control category-nameAr required" 
												type="text" 
												name="nameAr"
												placeholder="<fmt:message key="categories.nameAr_placeholder"/>"
												value="${category.nameAr}">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12 col-md-6">
										<div class="form-group form-group-lg">
											<select name="parent" class="category-parent">
												<option value="0"><fmt:message key="categories.parent"/></option>
												<c:forEach var="parentCategory" items="${parentCategories}">
													<option value="${parentCategory.id}" 
													${ parentCategory.id == category.parent ? 'selected' : '' }
													>${parentCategory.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="col-xs-12 col-md-6">
										<div class="form-group form-group-lg">
											<input 
												class="form-control category-ordering" 
												type="text" 
												name="ordering"
												placeholder="<fmt:message key="categories.ordering_placeholder"/>"
												value="${category.ordering}">
											
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group form-group-lg">
											<textarea 
													class="form-control category-description" 
													name="description"
													placeholder="<fmt:message key="categories.description_placeholder"/>">${category.description}</textarea>
										</div>	
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group form-group-lg">
											<textarea 
													class="form-control category-descriptionAr"
													name="descriptionAr"
													placeholder="<fmt:message key="categories.descriptionAr_placeholder"/>">${category.descriptionAr}</textarea>
										</div>	
									</div>
								</div>
								<div class="row">
									<div class="options form-group form-group-lg">
										<div class="col-xs-12 col-sm-4">
											<span class="option">
												<label class="top" for="isVisible"><fmt:message key="categories.visibility"/></label>
												<span id="isVisible">
													<input
														id="isVisible-yes" 
														type="radio" 
														name="isVisible" value="1" 
														${category.isVisible == 1 ? 'checked' : ''}>
														<label for="isVisible-yes"><fmt:message key="notify.yes"/></label>
													<input
														id="isVisible-no" 
														type="radio" 
														name="isVisible" value="0"
														${category.isVisible == 0 ? 'checked' : ''}>
														<label for="isVisible-no"><fmt:message key="notify.no"/></label>
												</span>
											</span>
										</div>
										<div class="col-xs-12 col-sm-4">
											<span class="option">
												<label class="top" for="isCommentable"><fmt:message key="categories.commentablity"/></label>
												<span id="isCommentable">
													<input
														id="isCommentable-yes"
														type="radio" 
														name="isCommentAllowed" value="1"
														${category.isCommentAllowed == 1 ? 'checked' : ''}>
														<label for="isCommentable-yes"><fmt:message key="notify.yes"/></label>
													<input
														id="isCommentable-no"
														type="radio" 
														name="isCommentAllowed" value="0"
														${category.isCommentAllowed == 0 ? 'checked' : ''}>
														<label for="isCommentable-no"><fmt:message key="notify.no"/></label>
												</span>
											</span>
										</div>
										<div class="col-xs-12 col-sm-4">
											<span class="option">
												<label class="top" for="isAdsable"><fmt:message key="categories.adsAbility"/></label>
												<span id="isAdsable">
													<input
														id="isAdsable-yes"
														type="radio" 
														name="isAdsAllowed" value="1"
														${category.isAdsAllowed == 1 ? 'checked' : ''}>
														<label for="isAdsable-yes"><fmt:message key="notify.yes"/></label>
													<input
														id="isAdsable-no" 
														type="radio" 
														name="isAdsAllowed" value="0"
														${category.isAdsAllowed == 0 ? 'checked' : ''}>
														<label for="isAdsable-no"><fmt:message key="notify.no"/></label>
												</span>
											</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12 col-sm-4"> 
										<div class="form-group">
											<input class="btn btn-primary btn-lg" type="submit" value="<fmt:message key="categories.editCategoryBtn"/>">
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
				<h1 class="text-center"><fmt:message key="categories.addNewCategory"/></h1>	
				<form class="custom-form categories-form" action="category_servlet.do" method="POST">
					<input type="hidden" name="action" value="add" >
					<div class="row">
						<div class="col-xs-12 col-md-6">
							<div class="form-group form-group-lg">
								<input 
									class="form-control category-name required" 
									type="text" 
									name="name"
									autocomplete="off"
									placeholder="<fmt:message key="categories.name_placeholder"/>">
							</div>	
						</div>
						<div class="col-xs-12 col-md-6">
							<div class="form-group form-group-lg">
								<input 
									class="form-control category-nameAr required" 
									type="text" 
									name="nameAr"
									autocomplete="off"
									placeholder="<fmt:message key="categories.nameAr_placeholder"/>">
							</div>			
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-md-6">
							<div class="form-group form-group-lg">
								<select name="parent" class="category-parent">
									<option value="0"><fmt:message key="categories.parent"/></option>
									<c:forEach var="category" items="${parentCategories}">
										<option value="${category.id}">${category.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-xs-12 col-md-6">
							<div class="form-group form-group-lg">
								<input 
									class="form-control category-ordering" 
									type="text" 
									name="ordering"
									placeholder="<fmt:message key="categories.ordering_placeholder"/>">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="form-group form-group-lg">
								<textarea 
										class="form-control category-description"
										name="description"
										placeholder="<fmt:message key="categories.description_placeholder"/>"
										></textarea>	
							</div>		
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="form-group form-group-lg">
								<textarea 
										class="form-control category-descriptionAr"
										name="descriptionAr"
										placeholder="<fmt:message key="categories.descriptionAr_placeholder"/>"></textarea>	
							</div>
						</div>
					</div>
					<div class="row">
						<div class="options form-group form-group-lg">
							<div class="col-xs-12 col-sm-4">
								<span class="option">
									<label class="top" for="isVisible"><fmt:message key="categories.visibility"/></label>
									<span id="isVisible">
										<input
											id="isVisible-yes"
											type="radio" 
											name="isVisible" checked="checked" value="1">
											<label for="isVisible-yes"><fmt:message key="notify.yes"/></label>
										<input
											id="isVisible-no"
											type="radio" 
											name="isVisible" value="0">
											<label for="isVisible-no"><fmt:message key="notify.no"/></label>
									</span>
								</span>
							</div>
							<div class="col-xs-12 col-sm-4">
								<span class="option">
									<label class="top" for="isCommentable"><fmt:message key="categories.commentablity"/></label>
									<span id="isCommentable">
										<input
											id="isCommentable-yes"
											type="radio" 
											name="isCommentAllowed" checked="checked" value="1">
											<label for="isCommentable-yes"><fmt:message key="notify.yes"/></label>
										<input
											id="isCommentable-no"
											type="radio" 
											name="isCommentAllowed" value="0">
											<label for="isCommentable-no"><fmt:message key="notify.no"/></label>
									</span>
								</span>
							</div>
							<div class="col-xs-12 col-sm-4">
								<span class="option">
									<label class="top" for="isAdsable"><fmt:message key="categories.adsAbility"/></label>
									<span id="isAdsable">
										<input
											id="isAdsable-yes"
											type="radio" 
											name="isAdsAllowed" checked="checked" value="1">
											<label for="isAdsable-yes"><fmt:message key="notify.yes"/></label>
										<input
											id="isAdsable-no"
											type="radio" 
											name="isAdsAllowed" value="0">
											<label for="isAdsable-no"><fmt:message key="notify.no"/></label>
									</span>
								</span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-4"> 
							<input class="btn btn-primary btn-lg" type="submit" value="<fmt:message key="categories.addCategory"/>">
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
						<c:if test="${isCategory == false}">
							<div class="fail-msg form-group wow slideInDown">
								<div>
									<i class="fa fa-close fa-3x"></i>
									<span><fmt:message key="error.isCategory"/></span>
								</div>
							</div>
						</c:if>
						<c:if test="${isCategoryExist == true}">
							<div class="fail-msg form-group wow slideInDown">
								<div>
									<i class="fa fa-close fa-3x"></i>
									<span><fmt:message key="error.categoryAlreadyExist"/></span>
								</div>
							</div>
						</c:if>
					</div>
					<div class="categories">
						<h1 class="text-center"><fmt:message key="categories.categories"/></h1>
						<a class="btn-add btn btn-primary" href="${add_category}">
							<i class="fa fa-plus"></i>
							<fmt:message key="categories.addCategory"/>
						</a>
						<div class="panel panel-default">
							
							<div class="panel-heading">
								<span class="head-title">
									<i class="fa fa-edit"></i>
								 	<span><fmt:message key="categories.manageCategories"/></span>
							 	</span>
								 <c:if test="${categories != null && categories.size() != 0 }">	
									 <div class="cats-view pull-right hidden-sm hidden-xs">
									 	<i class="fa fa-eye"></i>
									 	<fmt:message key="categories.view"/> : 
									 	<span data-value="full" class="active"><fmt:message key="categories.full"/></span> |	
									 	<span data-value="classic"><fmt:message key="categories.classic"/></span>
									 </div>
									 <div class="cats-order pull-right hidden-sm hidden-xs">
									 	<i class="fa fa-sort"></i>
									 	<fmt:message key="categories.order"/> : 
										<a class="${order == 'desc' ? 'active' : ''}" href="${desc_order}"><fmt:message key="categories.desc"/></a> |
										<a class="${order == 'asc' ? 'active' : ''}" href="${asc_order}"><fmt:message key="categories.asc"/></a>
									 </div>
								 </c:if>
							</div>
							<c:choose>
								<c:when test="${categories != null && categories.size() != 0 }">
									<div class="panel-body">
										<c:if test="${categories != null && categories.size() != 0 }">
											 <div class="cats-view visible-sm visible-xs text-center">
											 	<i class="fa fa-eye"></i>
											 	<fmt:message key="categories.view"/> : 
											 	<span data-value="full" class="active"><fmt:message key="categories.full"/></span> |	
											 	<span data-value="classic"><fmt:message key="categories.classic"/></span>
											 </div>
											 <div class="cats-order visible-sm visible-xs text-center"> 
											 	<i class="fa fa-sort"></i>
											 	<fmt:message key="categories.order"/> : 
												<a class="${order == 'desc' ? 'active' : ''}" href="${desc_order}"><fmt:message key="categories.desc"/></a> |
												<a class="${order == 'asc' ? 'active' : ''}" href="${asc_order}"><fmt:message key="categories.asc"/></a>
											 </div>
										 </c:if>
										<c:forEach var="category" items="${categories}">
											<div class="category">
												<h3>
													<span class="row-id" style="display: none;">
														${category.id}
													</span>
													<c:choose>
														<c:when test="${language eq 'en'}">
															${category.name}	
														</c:when>
														<c:otherwise>
															${category.nameAr}
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${category.description == null or category.description == ''}">
															<span class="no-desc"><fmt:message key="notify.no_discription_available"/></span>	
														</c:when>
														<c:otherwise>
															<c:choose>
																<c:when test="${language eq 'en'}">
																	<span>${category.description}</span>
																</c:when>
																<c:otherwise>
																	<span>${category.descriptionAr}</span>
																</c:otherwise>
															</c:choose>
														</c:otherwise>
													</c:choose>
												</h3>
												<div class="cat-options">
													<c:if test="${category.isVisible == 0}">
														<span class="is-visible-false">
															<i class="fa fa-eye-slash" aria-hidden="true"></i>
														</span>
														<span class="vis-false hidden-sm hidden-xs">
															<fmt:message key="categories.hiddenCategory"/>
														</span>
													</c:if>
													<c:if test="${category.isCommentAllowed == 0}">
														<span class="is-comment-allowed-false">
															<i class="fa fa-comments-o" aria-hidden="true"></i>
														</span>
														<span class="comm-false hidden-sm hidden-xs">
															<fmt:message key="categories.commentDissabled"/>
														</span>
													</c:if>
													<c:if test="${category.isAdsAllowed == 0}">
														<span class="is-ads-allowed-false">
															<i class="fa fa-buysellads" aria-hidden="true"></i>
														</span>
														<span class="ads-false hidden-sm hidden-xs">
															<fmt:message key="categories.adsDissabled"/>
														</span>
													</c:if>
													<div class="hidden-btns">
														<a href="${delete_category}&id=${category.id}" class="btn btn-primary cat-btn-delete confirm" title="<fmt:message key="notify.delete"/>">
															<i class="fa fa-close fa-lg"></i>
														</a>
														<a href="${edit_category}&id=${category.id}" class="btn btn-primary cat-btn-edit" title="<fmt:message key="notify.edit"/>">
															<i class="fa fa-edit fa-lg"></i>
														</a>
													</div>
												</div>
												<c:if test="${fn:length(func:getChildCategories(applicationScope['dbConn'],category.id)) > 0}">
													<div class="child-categories">
														<ul class="list-unstyled row">
															<c:forEach var="childObject" items="${func:getChildCategories(applicationScope['dbConn'],category.id)}">
																<li class="child col-md-4  col-lg-offset-2  col-xs-6 col-xs-offset-2">    
																	<c:choose>
																		<c:when test="${language eq 'en'}">
																			${childObject.name}
																		</c:when>
																		<c:otherwise>
																			${childObject.nameAr}
																		</c:otherwise>
																	</c:choose>
																	<p class="anchor-action" href="#">
																		<span class="anchor-action-arrow"></span>
																		<a class="edit" href="category_servlet.do?action=edit&id=${childObject.id}"><fmt:message key="notify.edit"/></a> | <a class="delete confirm" href="category_servlet.do?action=delete&id=${childObject.id}"><fmt:message key="notify.delete"/></a>
																	</p>
																</li>
															</c:forEach>
														</ul>
													</div>
												</c:if>
											</div>
											<hr>
										</c:forEach>
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