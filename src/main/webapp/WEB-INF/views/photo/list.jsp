<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진리스트보기</title>
<link rel="stylesheet" href="${path}/resource/css/photo/List.css">
</head>
<body>
	<main class="main">
		<div class="list-layer">
			<c:forEach var="n" items="${list}">
			<c:set var="TextValue" value="${n.src}"/>	
			<c:set var="reTextValue" value="${fn:replace(TextValue, '\', '/')}" />
				<div class="img-card">
					<a href="${path}/photo/detail/${n.id}">
						<img src="${fn:substringAfter(reTextValue,'wtpwebapps')}"/>
					</a>
				</div>
			</c:forEach>
		</div>
			
		
		<div class="tag-section">
			<div class="tag-section-child">#태그1#태그2#태그3</div>
			<div class="tag-section-child">#태그1#태그2#태그3</div>
			<div class="tag-section-child">#태그1#태그2#태그3</div>
		</div>
	</main>
</body>
</html>