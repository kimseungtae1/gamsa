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
					<!-- <ul>
						<li>생물
							<ul>
								<li><a class="cursor atag" href="">동물</a></li>
								<li><a class="cursor atag" href="">식물</a></li>
								<li><a class="cursor atag" href="">곤충</a></li>
							</ul>
						</li>
						<li>음식
							<ul>
								<li><a class="cursor atag" href="">요리</a></li>
								<li><a class="cursor atag" href="">조리</a></li>
								<li><a class="cursor atag" href="">식사</a></li>
							</ul>
						</li>
						<li>공간
							<ul>
								<li><a class="cursor atag" href="">카페</a></li>
								<li><a class="cursor atag" href="">책방</a></li>
								<li><a class="cursor atag" href="">마당</a></li>
								<li><a class="cursor atag" href="">기타</a></li>
							</ul>
						</li>
						<li>생활
							<ul>
								<li><a class="cursor atag" href="">가족</a></li>
								<li><a class="cursor atag" href="">아이</a></li>
								<li><a class="cursor atag" href="">친구</a></li>
								<li><a class="cursor atag" href="">연인</a></li>
							</ul>
						</li>
						<li>일상
							<ul>
								<li><a class="cursor atag" href="">패션</a></li>
								<li><a class="cursor atag" href="">여유</a></li>
								<li><a class="cursor atag" href="">순간</a></li>
							</ul>
						</li>
						<li>계절
							<ul>
								<li><a class="cursor atag" href="">봄</a></li>
								<li><a class="cursor atag" href="">여름</a></li>
								<li><a class="cursor atag" href="">가을</a></li>
								<li><a class="cursor atag" href="">겨울</a></li>
							</ul>
						</li>
						<li>풍경
							<ul>
								<li><a class="cursor atag" href="">자연</a></li>
								<li><a class="cursor atag" href="">도시</a></li>
								<li><a class="cursor atag" href="">야경</a></li>
								<li><a class="cursor atag" href="">건축</a></li>
							</ul>
						</li>
					</ul> -->
			</div>
			
			<div class="menu-list iblock">
				<ul>
					<li>
						<a class="cursor atag">사진첩</a>
						<ul>
							<li><a href="" class="cursor atag">인기사진100</a></li>
							<li><a href="${path}/photo/list" class="cursor atag">감성사진</a></li>
							<li><a href="" class="cursor atag">인기작가사진</a></li>
						</ul>
					</li>
					<li>
						<a class="cursor atag">커뮤니티</a>
						<ul>
							<li><a href="${path}/admin/board/notice" class="cursor atag">공지사항</a></li>
							<li><a href="${path}/qna/list?p=1" class="cursor atag">문의사항</a></li>
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
