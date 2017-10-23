<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

	<header id="header" class="header">
	
		<div class="content-container header-menu font">
			<div class="logo iblock">
				<a class="cursor"><img alt="로고" src=""></a>
			</div>
			
			<div class="categories iblock">
				<a class="cursor">
					<img src="${path}/resource/images/hamberger-menu.png" 
					alt="카테고리"/>
				</a>
			</div>
			
			<div class="menu-list iblock">
				<ul>
					<li>
						<a class="cursor">사진첩</a>
						<ul>
							<li><a class="cursor">인기사진100</a></li>
							<li><a class="cursor">감성사진</a></li>
							<li><a class="cursor">인기작가사진</a></li>
						</ul>
					</li>
					<li>
						<a class="cursor">커뮤니티</a>
						<ul>
							<li><a class="cursor">공지사항</a></li>
							<li><a class="cursor">문의사항</a></li>
						</ul>
					</li>
					<li><a class="cursor">태그</a></li>
				</ul>
			</div>
			
			
			<div class="gnb iblock">
				<ul>
					<li><a class="cursor">회원정보</a></li>
					<li><a class="cursor">사진올리기</a></li>
				</ul>
			</div>
			
			<div class="search-btn iblock">
				<a class="cursor"><img src="" alt="태그검색"></a>
			</div>
		</div>
		
	</header>
