<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진상세페이지</title>
<link rel="stylesheet" href="${path}/resource/css/qna/list.css">
<link rel="stylesheet" href="${path}/resource/css/qna/reg.css">
<link rel="stylesheet" href="${path}/resource/css/qna/reply.css">
<link rel="stylesheet" href="${path}/resource/css/photo/detail.css">

</head>
<body class="main">
	<div class="view_wrap">
		<c:set var="TextValue" value="${p.src}" />
		<c:set var="reTextValue" value="${fn:replace(TextValue, '\', '/')}" />
		<table class="board">
			<tr>
				<th>번호</th>
				<td>${p.id}</td>
				<th>제목</th>
				<td>${p.title}</td>
				<th>게시일</th>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${p.regDate}" /></td>
			</tr>
		</table>
		<div class="img_area"><img src="${fn:substringAfter(reTextValue,'wtpwebapps')}" ></div>

	</div>
	
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


	<%-- 		<c:set var="TextValue" value="${p.src}"/>
		<c:set var="reTextValue" value="${fn:replace(TextValue, '\', '/')}" />
		<img src="${fn:substringAfter(reTextValue,'wtpwebapps')}" /><br/>


	<table class="board">
		<tr>
			<th>번호</th>
			<td>${p.id}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${p.title}</td>
		</tr>
		<tr>
			<th>게시일</th>
			<td><fmt:formatDate pattern="yyyy-MM-dd"
					value="${p.regDate}" /></td>
		</tr>
		
		<tr>
			<th>내용</th>
			<td>${p.explain}</td>
		</tr>

	</table> --%>

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="tag-section">
		<div class="tag-section-child">#태그1#태그2#태그3</div>
		<div class="tag-section-child">#태그1#태그2#태그3</div>
		<div class="tag-section-child">#태그1#태그2#태그3</div>
	</div>
	</main>
</body>
</html>