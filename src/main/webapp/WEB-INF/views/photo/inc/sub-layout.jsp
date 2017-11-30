<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${path}/resource/css/content-layout.css">
<link rel="stylesheet" href="${path}/resource/css/header.css">
<link rel="stylesheet" href="${path}/resource/css/visual.css">
<link rel="stylesheet" href="${path}/resource/css/main.css">
<link rel="stylesheet" href="${path}/resource/css/footer.css">
<link rel="stylesheet" href="${path}/resource/css/photo/Reg.css">
<link rel="stylesheet" href="${path}/resource/css/photo/list.css">
<!-- <meta name="viewport" content="width=device-width,initial-scale=1"> -->
<title></title>
</head>
<body>
	<!-- header 부분 -->
	<tiles:insertAttribute name="header" />
	
	<tiles:insertAttribute name="visual" />
	<!-- --------------------------- <body> --------------------------------------- -->
	<div id="body">
		<div class="content-container clearfix">

			<tiles:insertAttribute name="main" />
			
		</div>
	</div>
	<!-- ------------------- <footer> --------------------------------------- -->
	<tiles:insertAttribute name="footer" />
</body>
</html>