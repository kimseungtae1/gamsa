<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx}/resource/css/content-layout.css">
<link rel="stylesheet" href="${ctx}/resource/css/header.css">
<link rel="stylesheet" href="${ctx}/resource/css/visual.css">
<link rel="stylesheet" href="${ctx}/resource/css/notice/list.css">
<link rel="stylesheet" href="${ctx}/resource/css/notice/reg.css">
<link rel="stylesheet" href="${ctx}/resource/css/footer.css">
<title></title>
</head>
<body>
	<!-- header 부분 -->
	<tiles:insertAttribute name="header" />
	<!-- --------------------------- <body> --------------------------------------- -->
	<div id="body">
		<div class="content-container clearfix">


			<!-- --------------------------- main --------------------------------------- -->
			
			<!-- content 부분 -->
			<tiles:insertAttribute name="main" />
			
		</div>
	</div>
	<!-- ------------------- <footer> --------------------------------------- -->
	<tiles:insertAttribute name="footer" />
</body>
</html>