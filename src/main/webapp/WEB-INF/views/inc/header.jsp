<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

	<header id="header" class="header">
	
		<div class="content-container header-menu font">
			<div class="logo iblock">
				<a href="${path}/index" class="cursor atag"><img alt="로고" src=""></a>
			</div>
			
			<div class="categories iblock">
				<a class="cursor atag">
					<img src="${path}/resource/images/hamberger-menu.png" 
					alt="카테고리"/>
				</a>
			</div>
			
			<div class="menu-list iblock">
				<ul>
					<li>
						<a class="cursor atag">사진첩</a>
						<ul>
							<li><a class="cursor atag">인기사진100</a></li>
							<li><a class="cursor atag">감성사진</a></li>
							<li><a class="cursor atag">인기작가사진</a></li>
						</ul>
					</li>
					<li>
						<a class="cursor atag">커뮤니티</a>
						<ul>
							<li><a class="cursor atag">공지사항</a></li>
							<li><a class="cursor atag">문의사항</a></li>
						</ul>
					</li>
					<li><a href="${path}/photo/upload/reg" class="cursor atag">사진올리기</a></li>
				</ul>
			</div>
			
			
			<div class="gnb iblock">
				<ul>
				<security:authorize access="!hasRole('ROLE_USER')">
					<li><a class="cursor atag" href="${path}/member/login">로그인</a></li>
					<li><a class="cursor atag" href="${path}/member/join">회원가입</a></li>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_USER')">
					<form action="${path}/logout" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<a class="cursor user-info-btn atag">회원정보</a>
						<!-- <li><a class="cursor">사진올리기</a></li> -->
						<input class="logout-btn" type="submit" value="로그아웃"/>
					</form>
				</security:authorize>
				</ul>
			</div>
			
			<div class="search-btn iblock">
				<a class="cursor atag"><img src="" alt="태그검색"></a>
			</div>
		</div>
		
	</header>
