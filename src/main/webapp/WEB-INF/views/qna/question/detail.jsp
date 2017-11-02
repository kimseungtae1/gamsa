<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<main class="main">
<div class="view_wrap">
	<table class="board">
		<tr>
			<th>번호</th>
			<td>${question.id}</td>
		</tr>
		<tr>
			<th>제목
			</th>
			<td>${question.title}</td>
		</tr>
		<tr>
			<th>게시일
			</th>
			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${question.regDate}" /></td>
		</tr>

	</table>
	<div class="page_num">
		<div class="content detail-content">${question.content}</div>

		<div class="reg-button">
			<a href="../list">목록으로</a>
		</div>
		

		<div class="reg-button"><%-- ../notice/edit/${question.id} --%>
			<a href="../edit/${question.id}">수정</a>
		</div>
	</div>
		


	</div>
	
	<div class="answer">
	<table class="board">
		<tr>
			<th>번호</th>
			<td>${answer.id}</td>
		</tr>
		<tr>
			<th>제목
			</th>
			<td>${answer.title}</td>
		</tr>
		<tr>
			<th>게시일
			</th>
			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${answer.regDate}" /></td>
		</tr>

	</table>
	<div class="page_num">
		<div class="content detail-content">${answer.content}</div>
		
		<c:if test="${not empty answer.content}">
		<div class="reg-button">
			<a href="../list">수정</a>
		</div>
		</c:if>		
		<c:if test="${empty answer.content}">
		<div class="reg-button">
			<a href="../list">등록</a>
		</div>
		</c:if>

<%-- 		<div class="reg-button">../notice/edit/${question.id}
			<a href="../edit/${answer.id}">수정</a>
		</div> --%>
	</div>
		


	</div>
	
</main>