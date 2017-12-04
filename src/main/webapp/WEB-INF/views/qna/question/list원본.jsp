<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${question.regDate}" /></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 리스트 : 이전  1 2 3 4 5 ... 다음 -->
	<c:set var="page" value="${param.p}" />
	<c:set var="startNum" value="${page-((page-1)%5)}" />
	<c:set var="lastNum"
		value="${fn:substringBefore(count%10 ==0 ? count/10 : count/10+1, '.')}" />


	<div class="listnum">

		<div>
			<!-- <a href="?p=1">이전</a> -->
			
			<c:if test="${1 < page-5 }">
				<a href="?p=${page-5}">이전</a>
			</c:if>
			 
		</div>
		<ul>
			<c:forEach var="i" begin="0" end="4">
<%-- 				<c:if test="${page<lastNum}"> --%>
					<c:set var="strong" value="" />
					<c:if test="${page == startNum+i }">
						<c:set var="strong" value="text-strong" />
					</c:if>
					<c:if test="${startNum+i <= lastNum }">
						<li><a class="w3-button"  href="?p=${startNum+i}">${startNum+i}</a></li>
					</c:if>
					<c:if test="${startNum+i > lastNum }">
						<li><a class="w3-button" href="?p=${startNum+i}">${startNum+i}</a></li>
					</c:if>
<%-- 				</c:if> --%>
			</c:forEach>
		</ul>
		<div>
			<c:if test="${lastNum >= startNum+5 }">
				<a href="?p=${startNum+5}">다음</a>
			</c:if>
		</div>
			<div class="reg-button">
		<a href="reg">글 쓰기</a>
	</div>
	</div>



</div>



</main>
