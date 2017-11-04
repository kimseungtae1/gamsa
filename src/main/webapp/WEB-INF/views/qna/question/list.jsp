<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<main class="main">
		<h1>질의응답</h1>
	
	<div class="view_wrap">
		<table class="board">
			<thead>
				<td>번호</td>
				<td class="title">제목</td>
				<td>게시자</td>
				<td>게시일</td>
			</thead>
			<tbody>
				<c:forEach var="question" items="${list}">					
					<tr>
						<td>${question.id}</td>
						<td class="title indent"><a href="detail/${question.id}">${question.title}</a></td>
						<td>${question.writerId}</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${question.regDate}" />		
						</td>
						
					</tr>
					</c:forEach>
			</tbody>
		</table>
		<div class="page_num">
		<div>
		1
		
		</div>

		<div class="reg-button">
					<a  href="reg">글 쓰기</a>
		</div>

	</div>



</main>		