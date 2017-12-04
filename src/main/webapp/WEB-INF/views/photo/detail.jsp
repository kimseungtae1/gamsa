<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진상세페이지</title>
</head>
<body>
	<main id="main">
		<%-- <img class="img-box" src="${path}/resource/images/key1.JPG"/>
		<img class="img-box" src="${path}/resource/images/key1.JPG"/>
		<img class="img-box" src="${path}/resource/images/key1.JPG"/>
		<img class="img-box" src="${path}/resource/images/key1.JPG"/>
		<img class="img-box" src="${path}/resource/images/key1.JPG"/>
		<img class="img-box" src="${path}/resource/images/key1.JPG"/> --%>
		<%-- <c:forEach var="n" items="${list}">
		<c:set var="TextValue" value="${n.src}"/>
			${n.src}<br/>	
			<c:set var="reTextValue" value="${fn:replace(TextValue, '\', '/')}" />
			<img src="${fn:substringAfter(reTextValue,'wtpwebapps')}" /><br/>
			<hr/>
		</c:forEach> --%>
		<%-- id : ${p.id}<br/>
		title : ${p.title}<br/>
		explain : ${p.explain}<br/>
		regDate : ${p.regDate}<br/>
		liked : ${p.liked}<br/>
		replyId : ${p.replyId}<br/>
		writerId : ${p.writerId}<br/> 
		photoId : ${p.photoId}<br/>--%>
		<c:set var="TextValue" value="${p.src}"/>
		<c:set var="reTextValue" value="${fn:replace(TextValue, '\', '/')}" />
		<img src="${fn:substringAfter(reTextValue,'wtpwebapps')}" /><br/>

		<div class="tag-section">
			<div class="tag-section-child">#태그1#태그2#태그3</div>
			<div class="tag-section-child">#태그1#태그2#태그3</div>
			<div class="tag-section-child">#태그1#태그2#태그3</div>
		</div>
	</main>
</body>
</html>