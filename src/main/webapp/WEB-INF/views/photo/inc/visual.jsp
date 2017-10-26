<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<div id="visual" class="visual">

	<ul id="slides">
		<li class="slide showing"></li>
		<li class="slide"></li>
		<li class="slide"></li>
		<li class="slide"></li>
		<li class="slide"></li>
	</ul>
	
	<script type="text/javascript">
		var slides = document.querySelectorAll('#visual #slides .slide');
		var currentSlide = 0;
		var slideInterval = setInterval(nextSlide,3000);
	
		function nextSlide(){
			slides[currentSlide].className = 'slide';
			currentSlide = (currentSlide+1)%slides.length;
			slides[currentSlide].className = 'slide showing';
		};
	</script>
	

	<div class="key-text content-container">
		<h1>당신의 추억</h1>
		<h1>당신의 사진</h1>
		<h1>그리고 감성</h1>
		<form class="form" action="">
			<input class="key-img-search" type="text" name="" value="" 
				placeholder="Image search button" />
			<!-- <button type="button" class="key-img-search-btn"></button> -->
			<a href="">
				<img class="key-img-search-btn" alt="키이미지 검색버튼" 
				src="${path}/resource/images/search-btn.png"/>
			</a>
		</form>
	</div>

</div>
