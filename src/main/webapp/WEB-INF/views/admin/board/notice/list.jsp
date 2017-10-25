<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<main>
	
	
	<div class="view_wrap">
		<table class="board">
			<thead>
				<th>번호</th>
				<td class="title">제목</td>
				<td>게시자</td>
				<td>게시일</td>
			</thead>
			<tbody>
				<c:forEach var="n" items="${list}">					
					<tr>
						<td>${n.id}</td>
						<td class="title indent"><a href="notice/${n.id}">${n.title}</a></td>
						<td>${n.writerId}</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${n.regDate}" />		
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
					<a  href="notice/reg">글 쓰기</a>
		</div>

	</div>



</main>		