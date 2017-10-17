<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

	<header id="header" class="header">
	
		<div class="content-container header-menu font">
			<div class="logo iblock">
				<img alt="로고" src="">
			</div>
			
			<div class="categories iblock">
				<img src="${path}/resource/images/hamberger-menu.png" alt="카테고리"/>
			</div>
			
			<div class="menu-list iblock">
				<ul>
					<li>사진첩</li>
					<li>커뮤니티</li>
					<li>태그</li>
				</ul>
				<!-- <div>
					<ul>
						<li>갬사사진</li>
						<li>인기사진100</li>
					</ul>
				</div>
				<div>
					<ul>
						<li>공지사항</li>
						<li>문의사항</li>
					</ul>
				</div> -->
				
			</div>
			<div class="gnb iblock">
				<ul>
					<li>회원정보</li>
					<li>사진올리기</li>
				</ul>
			</div>
			
			<div class="search-btn iblock">
				<img src="" alt="태그검색">
			</div>
		</div>
		
	</header>
