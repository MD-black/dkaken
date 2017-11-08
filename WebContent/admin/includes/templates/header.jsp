<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
		<title>${param.title}</title>
		<link rel="stylesheet" href="http://fonts.googleapis.com/earlyaccess/droidarabickufi.css"/>	
		<c:choose>
			<c:when test="${sessionScope.language eq 'en' }">
				<link rel="stylesheet" href="${initParam.css_dir}bootstrap.min.css">
					
			</c:when>
			<c:otherwise>
				<link rel="stylesheet" href="${initParam.css_dir}bootstrap.min.css">
				<link rel="stylesheet" href="${initParam.css_dir}bootstrap-rtl.min.css">
			</c:otherwise>
		</c:choose>
		
		
		<link rel="stylesheet" href="${initParam.css_dir}bootstrap-tagsinput.css">
		
		
		<link rel="stylesheet" href="${initParam.dt_dir}datatables.css"/>
		<link rel="stylesheet" href="${initParam.css_dir}font-awesome.min.css">
		<link rel="stylesheet" href="${initParam.css_dir}jquery-ui.css">
		<link rel="stylesheet" href="${initParam.css_dir}jquery.selectBoxIt.css">
		<link rel="stylesheet" href="${initParam.css_dir}animate.css">
		<c:choose>
			<c:when test="${sessionScope.language eq 'en' }">
				<link rel="stylesheet" href="${initParam.css_dir}style.css">	
			</c:when>
			<c:otherwise>
				<link rel="stylesheet" href="${initParam.css_dir}style.css">
				<link rel="stylesheet" href="${initParam.css_dir}style-rtl.css">
			</c:otherwise>
		</c:choose>
		
		
	</head>
	<body>