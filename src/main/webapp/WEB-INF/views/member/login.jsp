<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<main id="main">
		<div class="login-wrap">
			<div class="disp-table">
				<div class="disp-table-cell">
					<form action="${path}/login" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<div class="view-wrap">
							<h1>로그인.</h1>
							<p class="ep-text">간단한 로그인으로 더 많은 사진을 이용하세요!</p>
							<div class="login-box">
								<input type="text" name="id" placeholder="이메일"/>
								<input type="text" name="pwd" placeholder="비밀번호"/>
								<input type="submit" value="로그인">
								<div>
									<label>
										<input type="checkbox" value="1"/>로그인상태유지
									</label>
									<a href="${path}/gamsung/member/join" class="atag-btn">회원가입</a>
									<a href="${path}/gamsung/member/searchPw" class="atag-btn">비밀번호찾기</a>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</main>
</body>
</html>