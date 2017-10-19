<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${path}/resource/css/content-layout.css">
<link rel="stylesheet" href="${path}/resource/css/header.css">
<link rel="stylesheet" href="${path}/resource/css/visual.css">
<link rel="stylesheet" href="${path}/resource/css/main.css">
<title>갬사 메인페이지</title>
</head>
<body>
	<tiles:insertAttribute name="header"/>
	
	<tiles:insertAttribute name="visual"/>
	
	<tiles:insertAttribute name="main"/>
	
	<tiles:insertAttribute name="footer"/>
</body>
</html>