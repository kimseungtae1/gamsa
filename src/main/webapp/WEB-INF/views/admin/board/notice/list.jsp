<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<main class="main">
	<h1>공지사항</h1>
	
	<div class="view_wrap">

		<table class="board">
			<thead>
				<td>번호</td>
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
	
<%-- 	<div>
	<c:set var="page" value="${param.p}"/>	<!--view에서도 파라미터에서 값을 탐색  -->
		<c:set var="startNum" value="${page-(page-1)%5}"/>
		<c:set var="lastNum" value="${fn:substringBefore((count%10 == 0 ? count/10 : count/10+1), '.')}"/>
		
		<div>
			<div><a href="?p=1">이전</a></div>
				<ul>
					<c:forEach var="i" begin="0" end="4">
						<!-- 현재 페이지 번호 오렌지색으로 표시 // 임시변수 이용!-->
						<c:set var="strong" value=""/>
						<c:if test="${page == startNum+i}">
							<c:set var="strong" value="text-strong"/><!-- text-strong은 style에 class이름임! -->
						</c:if>
						<c:if test="${startNum + i <= lastNum}">
							<li><a class="${strong}" href="?p=${startNum+i}">${startNum+i}</a></li>
						</c:if>
						
						<c:if test="${startNum + i > lastNum}">
							<li>${startNum+i}</li>
						</c:if>
					</c:forEach>
				</ul>
			<div>
				<c:if test="${lastNum >= startNum+5}">
				<a href="?p=${startNum+5}">다음</a>
				</c:if>
			</div>
		</div>
	
	</div> --%>



</main>		