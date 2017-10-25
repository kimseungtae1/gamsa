<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갬사_Gamsa</title>
</head>
<body>
	<main id="main">
		<div class="content-container content-wrap">
			<div class="main-content-boxes">
				<div class="boxes">
					<h1>인기사진 바로보기</h1>
				</div>
				<div class="boxes">
					<h1>새로 올라온 사진들</h1>
				</div>
				<div class="boxes">
					<h1>사진 올리기</h1>
				</div>
			</div>
		</div>
		
		
		<div class="ex-section">
			<div class="content-wrap">
				<h1>누구나 끌리는 감성사진</h1>
				<h4 class="font-explain">갬사에서는 감성사진을 무료로 제공해드립니다.</h4>
				<h4 class="font-explain">오로지 감성만을 자극하는 사진들로 구성되어 있으니 편하게 이용하세요!</h4>
			</div>
		</div>
		
		
		<div class="img-list">
			<img class="img-box" src="${path}/resource/images/key1.JPG"/>
			<img class="img-box" src="${path}/resource/images/key1.JPG"/>
			<img class="img-box" src="${path}/resource/images/key1.JPG"/>
			<img class="img-box" src="${path}/resource/images/key1.JPG"/>
			<img class="img-box" src="${path}/resource/images/key1.JPG"/>
			<img class="img-box" src="${path}/resource/images/key1.JPG"/>
			<img class="img-box" src="${path}/resource/images/key1.JPG"/>
			<img class="img-box" src="${path}/resource/images/key1.JPG"/>
			<img class="img-box" src="${path}/resource/images/key1.JPG"/>
			<img class="img-box" src="${path}/resource/images/key1.JPG"/>
			<img class="img-box" src="${path}/resource/images/key1.JPG"/>
			<img class="img-box" src="${path}/resource/images/key1.JPG"/>
			<img class="img-box" src="${path}/resource/images/key1.JPG"/>
			<img class="img-box" src="${path}/resource/images/key1.JPG"/>
			<img class="img-box" src="${path}/resource/images/key1.JPG"/>
			<img class="img-box" src="${path}/resource/images/key1.JPG"/>
			<img class="img-box" src="${path}/resource/images/key1.JPG"/>
			<img class="img-box" src="${path}/resource/images/key1.JPG"/>
		</div>
		
		
		<div class="img-list">
			<div class="more-btn-div">
				<button class="more-btn" type="button">사진 더보기</button>
			</div>
		</div>
		
	</main>
</body>
</html>